import { Link } from "react-router-dom";

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-container">
                <div className="footer-section">
                    <h3 className="footer-heading">Socotra Airline</h3>
                    <p className="footer-text">
                        This site does not represent any authorization, it is built for learning purposes.
                    </p>
                </div>

                <div className="footer-section">
                    <h3 className="footer-heading">Quick Links</h3>
                    <ul className="footer-links">
                        <li><Link className="footer-link" to="/flights">Book Flight</Link></li>
                        <li><Link className="footer-link" to="/contact">Contact Us</Link></li>
                    </ul>
                </div>

                <div className="footer-section">
                    <h3 className="footer-heading">Legal</h3>
                    <ul className="footer-links">
                        <li><Link className="footer-link" to="/terms">Terms of Service</Link></li>
                        <li><Link className="footer-link" to="/privacy">Privacy Policy</Link></li>
                        <li><Link className="footer-link" to="/faq">FAQ</Link></li>
                    </ul>
                </div>
            </div>
            <div className="footer-bottom">
                  <p>&copy; 2025 Socotra Airlines. All rights reserved.</p>
  <p>Made with ❤️ for learning purposes</p>
            </div>
        </footer>
    );
};

export default Footer;
