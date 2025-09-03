import { useNavigate, Link } from "react-router-dom";
import { useMessage } from "../common/MessageDisplay";
import { useState } from "react";
import ApiService from "../../services/ApiService";

const RegisterPage = ()=>{

    const{ErrorDisplay, SuccessDisplay , showError,showSuccess} = useMessage();
    const navigate = useNavigate();

    const [formData , setFormData] = useState({
        name :'' ,
        email :'' ,
        password :'' ,
        phoneNumber :'' ,
        confirmedPassword :'' 
    });

    const handleChange =(e) =>{
        setFormData({...formData , [e.target.name] : e.target.value})
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if(!formData.name || !formData.email || !formData.password || !formData.phoneNumber || !formData.confirmedPassword){
            showError("All fields are required")
            return ; 
        }

        if(formData.password !== formData.confirmedPassword){
            showError("Passowrd and confirmed Passwrad must match !")
            return ; 


        }
        const registrationData = {
            name :formData.name , 
            email : formData.email , 
            password : formData.password , 
            phoneNumber : formData.phoneNumber 
        }; 

        try {
            const respone = await ApiService.registerUser(registrationData) ; 
            if (respone.statusCode === 200){
                showSuccess("User successfully registered")
                navigate("/login")
            }
            else {
                showError(respone.message) ; 
            }
        } catch (error) {

            showError(error.respone?.data?.message ||error.message) ; 
            
        }
    }

    return(
        <div className="auth-page">
            <div className="auth-card">
                <ErrorDisplay/>
                <SuccessDisplay/>
                <div className="auth-header">
                    <h2>Create your Account </h2>
                    <p>Join Socotra Airlines for seamless travel experiences</p>

                </div>

                <form className="auth-form" onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="">Full Name</label>
                        <input type="text"
                        name="name"
                        id="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                        placeholder="Enter your name ..." />
                    </div>

                     <div className="form-group">
                        <label htmlFor="">Email Address</label>
                        <input type="email"
                        name="email"
                        id="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                        placeholder="Enter your email ..." />
                    </div>

                     <div className="form-group">
                        <label htmlFor="">Phone Number</label>
                        <input type="tel"
                        name="phoneNumber"
                        id="phoneNumber"
                        value={formData.phoneNumber}
                        onChange={handleChange}
                        required
                        placeholder="Enter your phone number ..." />
                    </div>

                       <div className="form-group">
                        <label htmlFor="">Password</label>
                        <input type="password"
                        name="password"
                        id="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                        placeholder="Enter your password ..." />
                    </div>


                    <div className="form-group">
                        <label htmlFor="">Confirm Password</label>
                        <input type="password"
                        name="confirmedPassword"
                        id="confirmedPassword"
                        value={formData.confirmedPassword}
                        onChange={handleChange}
                        required
                        placeholder="Enter your password again ..." />
                    </div>
                    <button type="submit" className="auth-button">
                        Create Account
                    </button>

                    <div className="auth-footer">
                        <p>Already have an accounnt ? <Link to={"/login"}>Sign in here</Link>in </p>
                    </div>
                </form>
            </div>
        </div>
    )

}

export default RegisterPage ; 