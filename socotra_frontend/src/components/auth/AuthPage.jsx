import { useNavigate } from "react-router-dom";
import { useMessage } from "../common/MessageDisplay";
import { useState } from "react";
import ApiService from "../../services/ApiService";

const AuthPage = () => {
  const { ErrorDisplay, SuccessDisplay, showError, showSuccess } = useMessage();
  const navigate = useNavigate();
  const [isRegister, setIsRegister] = useState(false);

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    phoneNumber: "",
    confirmedPassword: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!formData.email || !formData.password) {
      showError("Email and Password are required");
      return;
    }

    try {
      const response = await ApiService.loginUser({
        email: formData.email,
        password: formData.password,
      });

      if (response.statusCode === 200) {
        ApiService.saveRoles(response.data.roles);
        ApiService.saveToken(response.data.token);
        showSuccess("Login successful");
        navigate("/");
      } else {
        showError(response.message);
      }
    } catch (error) {
      showError(error.response?.data?.message || error.message);
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    if (
      !formData.name ||
      !formData.email ||
      !formData.password ||
      !formData.phoneNumber ||
      !formData.confirmedPassword
    ) {
      showError("All fields are required");
      return;
    }

    if (formData.password !== formData.confirmedPassword) {
      showError("Password and Confirm Password must match!");
      return;
    }

    try {
      const response = await ApiService.registerUser({
        name: formData.name,
        email: formData.email,
        password: formData.password,
        phoneNumber: formData.phoneNumber,
      });

      if (response.statusCode === 200) {
        showSuccess("User successfully registered");
        setIsRegister(false);
        setFormData({
          name: "",
          email: "",
          password: "",
          phoneNumber: "",
          confirmedPassword: "",
        });
      } else {
        showError(response.message);
      }
    } catch (error) {
      showError(error.response?.data?.message || error.message);
    }
  };

  return (
    <div className="auth-page">
      <div className={`auth-card ${isRegister ? "register-active" : ""}`}>
        <ErrorDisplay />
        <SuccessDisplay />

        <div className="auth-wrapper">
          {/* Login Panel */}
          <div className="auth-panel">
            <h2>Welcome Back</h2>
            <p>Sign in to book your next flight ✈️</p>
            <form onSubmit={handleLogin}>
              <div className="form-group">
                <label>Email Address</label>
                <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  required
                  placeholder="Enter your email..."
                />
              </div>

              <div className="form-group">
                <label>Password</label>
                <input
                  type="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  required
                  placeholder="Enter your password..."
                />
              </div>

              <button type="submit" className="auth-button">
                Sign in
              </button>
            </form>

            <div className="switch-link" onClick={() => setIsRegister(true)}>
              Don’t have an account? Register
            </div>
          </div>

          {/* Register Panel */}
          <div className="auth-panel">
            <h2>Create Account</h2>
            <p>Join Socotra Airlines for seamless travel 🌍</p>
            <form onSubmit={handleRegister}>
              <div className="form-group">
                <label>Full Name</label>
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  required
                  placeholder="Enter your name..."
                />
              </div>

              <div className="form-group">
                <label>Email Address</label>
                <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  required
                  placeholder="Enter your email..."
                />
              </div>

              <div className="form-group">
                <label>Phone Number</label>
                <input
                  type="tel"
                  name="phoneNumber"
                  value={formData.phoneNumber}
                  onChange={handleChange}
                  required
                  placeholder="Enter your phone number..."
                />
              </div>

              <div className="form-group">
                <label>Password</label>
                <input
                  type="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  required
                  placeholder="Enter your password..."
                />
              </div>

              <div className="form-group">
                <label>Confirm Password</label>
                <input
                  type="password"
                  name="confirmedPassword"
                  value={formData.confirmedPassword}
                  onChange={handleChange}
                  required
                  placeholder="Re-enter your password..."
                />
              </div>

              <button type="submit" className="auth-button">
                Create Account
              </button>
            </form>

            <div className="switch-link" onClick={() => setIsRegister(false)}>
              Already have an account? Login
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AuthPage;
