import { Link, useNavigate } from "react-router-dom";
import ApiService from "../../services/ApiService";

const Navbar = () => {
    const isAuthenticated = ApiService.isAuthenicated();
    const isAdmin = ApiService.isAdmin?.();     // adjust based on your ApiService
    const isPilot = ApiService.isPilot?.();
    const isCustomer = ApiService.isCustomer?.();

    const navigate = useNavigate();

    const handleLogout = () => {
        const isLogout = window.confirm("Are you sure you want to log out?");
        if (isLogout) {
            ApiService.logout(); // <-- call function properly
            navigate("/login");
        }
    };

    return (
        <nav className="nb">
            <div className="nb-container">
                {/* Left: Brand */}
                <div className="nb-brand">
                    <Link to="/" className="nb-logo">
                        <span className="logo-airline">Socotra</span>
                        <span className="logo-text">Airline</span>
                         <span className="logo-plane">✈️</span>
                    </Link>
                </div>

                {/* Right: Links */}
                <div className="nb-links">
                    <Link to="/" className="nav-link">Home</Link>
                    <Link to="/flights" className="nav-link">Find Flights</Link>

                    {isAuthenticated ? (
                        <>
                            {isCustomer && (
                                <Link to="/profile" className="nav-link">Profile</Link>
                            )}
                            {isPilot && (
                                <Link to="/pilot" className="nav-link">Pilot</Link>
                            )}
                            {isAdmin && (
                                <Link to="/admin" className="nav-link">Admin</Link>
                            )}
                            <button className="nav-button" onClick={handleLogout}>
                                Logout
                            </button>
                        </>
                    ) : (
                        <>
                            <Link to="/login" className="nav-link">Login</Link>
                            <Link to="/register" className="nav-button nav-button-primary">
                                Register
                            </Link>
                        </>
                    )}
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
