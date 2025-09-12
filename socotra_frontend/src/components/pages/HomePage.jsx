import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import ApiService from "../../services/ApiService";
import { useMessage } from "../common/MessageDisplay";
import { useTranslation } from 'react-i18next';

const HomePage = () => {

    const { ErrorDisplay, SuccessDisplay, showError } = useMessage();
    const navigate = useNavigate();
    const [airports, setAirports] = useState([]);

    const [searchData, setSearchData] = useState({
        departureIataCode: "",
        arrivalIataCode: "",
        departureDate: ""
    });
    const { t, i18n } = useTranslation();
      useEffect(() => {
        if (i18n.language === 'ar') {
            document.documentElement.dir = 'rtl';
        } else {
            document.documentElement.dir = 'ltr';
        }
    }, [i18n.language]);

    const popularDestinations = [
        { id: 1, city: "Moscow", country: "RUSSIA", price: "$850", image: "moscow.png" },
        { id: 2, city: "Istanbul", country: "Turkey", price: "$580", image: "istanbul.png" },
        { id: 3, city: "Dubai", country: "UAE", price: "$420", image: "dubai.png" },
        { id: 4, city: "Alexandria", country: "Egypt", price: "$680", image: "alexandaria.png" }
    ]


    //fetch airport on component mount
    useEffect(() => {
        const fetchAirports = async () => {
            try {
                const respones = await ApiService.getAllAirports();
                setAirports(respones.data || [])
            } catch (error) {
                showError("Faild to load airports !")

            }

        }
        fetchAirports();
    }, []);

    const handlSearch = async (e) => {

        e.preventDefault();
        if (!searchData.departureIataCode || !searchData.arrivalIataCode || !searchData.departureDate) {
            showError("Please select departure, arrival, and date");
            return;
        }


        navigate(`/flights?departureIataCode=${searchData.departureIataCode}&arrivalIataCode=${searchData.arrivalIataCode}&departureDate=${searchData.departureDate}`);

    }

    const handlSwapAirports = () => {
        setSearchData({
            ...searchData,
            departureIataCode: searchData.arrivalIataCode,
            arrivalIataCode: searchData.departureIataCode,

        })
    }

    const formatAirportOption = (airport) => {
        return `${airport.iataCode} (${airport.city}) -(${airport.name})`

    }
    return (
     
    <div className="home-page">
        <div className="hero-section">
            <div className="hero-content">
                <h1>{t("Book-Airlines")}</h1>
                <p>{t("Find the best deals for your journey")}</p>
            </div>

            <div className="search-box">
                <ErrorDisplay />
                <SuccessDisplay />
                <form onSubmit={handlSearch}>
                    <div className="search-fields">
                        <div className="form-group">
                            <label htmlFor="">{t("From")}</label>
                            <select required value={searchData.departureIataCode}
                                onChange={(e) => setSearchData({ ...searchData, departureIataCode: e.target.value })}>
                                <option value="">{t("Select Departure Airport")}</option>
                                {airports.map(airport => (
                                    <option key={`dep-${airport.iataCode}`} value={airport.iataCode}>
                                        {formatAirportOption(airport)}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div className="swap-cities">
                            <button type="button"
                                onClick={handlSwapAirports}
                                aria-label={t("Swap Departure and Arrival")}>
                                ↔
                            </button>
                        </div>
                        
                        <div className="form-group">
                            <label htmlFor="">{t("To")}</label>
                            <select value={searchData.arrivalIataCode}
                                onChange={(e) => setSearchData({ ...searchData, arrivalIataCode: e.target.value })}>
                                <option value="">{t("Select Arrival Airport")}</option>
                                {airports.filter(airport => airport.iataCode !== searchData.departureIataCode)
                                    .map(airport => (
                                        <option key={`arr-${airport.iataCode}`} value={airport.iataCode}>
                                            {formatAirportOption(airport)}
                                        </option>
                                    ))}
                            </select>
                        </div>

                        <div className="form-group">
                            <label htmlFor="">{t("Departure Date")}</label>
                            <input
                                type="date"
                                required
                                value={searchData.departureDate}
                                onChange={(e) => setSearchData({
                                    ...searchData, departureDate: e.target.value
                                })}
                                min={new Date().toISOString().split('T')[0]} />
                        </div>
                    </div>

                    <button className="search-button" type="submit">
                        {t("Search Flights")}
                    </button>
                </form>
            </div>
        </div>

        {/* Popular Destinations */}
        <section className="popular-destinations">
            <h2>{t("Popular Destinations")}</h2>
            <p>{t("Explore our most booked flight routes")}</p>

            <div className="destinations-carousel">
                <div className="destinations-track">
                    {popularDestinations.map(destination => (
                        <div key={destination.id} className="destination-card">
                            <div className="destination-image" style={{ backgroundImage: `url(/images/${destination.image})` }}>
                                <div className="destination-overlay">
                                    <h3>{t(destination.city)}</h3>
                                    <p>{t(destination.country)}</p>
                                </div>
                            </div>
                            <div className="destination-footer">
                                <span>{t("From")} {destination.price}</span>
                                <Link to="/flights" className="book-button">{t("Book Now")}</Link>
                            </div>
                        </div>
                    ))}
                    {/* duplicate for seamless loop */}
                    {popularDestinations.map(destination => (
                        <div key={`dup-${destination.id}`} className="destination-card">
                            <div className="destination-image" style={{ backgroundImage: `url(/images/${destination.image})` }}>
                                <div className="destination-overlay">
                                    <h3>{t(destination.city)}</h3>
                                    <p>{t(destination.country)}</p>
                                </div>
                            </div>
                            <div className="destination-footer">
                                <span>{t("From")} {destination.price}</span>
                                <Link to="/flights" className="book-button">{t("Book Now")}</Link>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </section>

        {/* Why Choose Us */}
        <section className="features-section">
            <h2>{t("Why Choose Socotra Airlines?")}</h2>

            <div className="features-grid">
                <div className="feature-card">
                    <div className="feature-icon">✈️</div>
                    <h3>{t("Modern Fleet")}</h3>
                    <p>{t("Fly in comfort with our state-of-the-art aircraft featuring the latest amenities.")}</p>
                </div>

                <div className="feature-card">
                    <div className="feature-icon">🕒</div>
                    <h3>{t("On-Time Performance")}</h3>
                    <p>{t("We pride ourselves on our industry-leading punctuality record.")}</p>
                </div>

                <div className="feature-card">
                    <div className="feature-icon">🍽️</div>
                    <h3>{t("Gourmet Dining")}</h3>
                    <p>{t("Enjoy chef-curated meals inspired by global cuisines.")}</p>
                </div>

                <div className="feature-card">
                    <div className="feature-icon">💺</div>
                    <h3>{t("Extra Legroom")}</h3>
                    <p>{t("More space to relax with our generous seat pitch in all classes.")}</p>
                </div>
            </div>
        </section>
        
        {/* Special Offers */}
        <section className="offers-section">
            <h2>{t("Special Offers")}</h2>
            <p>{t("Don't miss these exclusive deals")}</p>

            <div className="offer-card">
                <div className="offer-content">
                    <h3>{t("Summer Sale - Up to 30% Off!")}</h3>
                    <p>{t("Book by June 30 for travel between July and September.")}</p>
                    <Link to="/flights" className="offer-button">{t("View Deals")}</Link>
                </div>
            </div>
        </section>

        {/* Testimonials */}
        <section className="testimonials-section">
            <h2>{t("What Our Passengers Say")}</h2>

            <div className="testimonials-grid">
                <div className="testimonial-card">
                    <div className="testimonial-text">
                        {t("The service was exceptional from booking to landing. Will definitely fly with Socotra again!")}
                    </div>
                    <div className="testimonial-author">
                        <div className="author-name">{t("Sarah Johnson")}</div>
                        <div className="author-detail">{t("Frequent Flyer")}</div>
                    </div>
                </div>

                <div className="testimonial-card">
                    <div className="testimonial-text">
                        {t("Most comfortable economy seats I've experienced. The crew made the long flight enjoyable.")}
                    </div>
                    <div className="testimonial-author">
                        <div className="author-name">{t("Michael Chen")}</div>
                        <div className="author-detail">{t("Business Traveler")}</div>
                    </div>
                </div>
            </div>
        </section>
    </div>
);



}
export default HomePage