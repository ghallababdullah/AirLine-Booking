import axios from "axios"

export default class ApiService {
    static BASE_URL = "http://localhost:8082/api";

    static saveToken(token) {

        localStorage.setItem("token", token);
    }

    static getToken() {

        return localStorage.getItem("token")
    }

    static saveRoles(roles) {

        localStorage.setItem("roles", JSON.stringify(roles))
    }

    static getRoles() {
        const roles = localStorage.getItem("roles");
        return roles ? JSON.parse(roles) : null;
    }

    static hasRole(role) {

        const roles = this.getRoles();
        return roles ? roles.innclude(role) : false;
    }

    static isAdmin() {
        return this.hasRole("Admin");
    }

    static isPilot() {
        return this.hasRole("Pilot");
    }

    static isCustomer() {
        return this.hasRole("Customer");
    }

    static logout() {

        localStorage.removeItem("token");
        localStorage.removeItem("roles")
    }

    static isAuthenicated() {
        const token = this.getToken();
        return !!token;
    }

    static getHeader() {
        const token = this.getToken;
        return {
            Authorization: 'Bearer  $()token',
            "Content-Type": "application/json"

        }
    }

    // Register a user
    static async registerUser(body) {
        const resp = await axios.post(`${this.BASE_URL}/auth/register`, body);
        return resp.data;
    }
    // login 
    static async loginUser(body) {
        const resp = await axios.post(`${this.BASE_URL}/auth/login`, body);
        return resp.data;
    }







    // User profile management

    static async getAccountDetails() {
        const resp = await axios.get(`${this.BASE_URL}/users/me`,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;


    }


    static async updateAccount(body) {
        const resp = await axios.put(`${this.BASE_URL}/users`, body,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;

    }

    static async getAllPilots() {
        const resp = await axios.put(`${this.BASE_URL}/users/pilots`,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;

    }


    // Airport api managment 

    static async creatAiport(body) {
        const resp = await axios.post(`${this.BASE_URL}/airports`, body,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;
    }

    static async updateAirport(body) {
        const resp = await axios.put(`${this.BASE_URL}/airports`, body,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;
    }


    static async getAllAirports() {
        const resp = await axios.get(`${this.BASE_URL}/airports`
        );
        return resp.data;

    }


    static async getAirportByid() {
        const resp = await axios.get(`${this.BASE_URL}/airports/${id}`
        );
        return resp.data;

    }

    // Booking methods api 


    static async creatBooking(body) {
        const resp = await axios.post(`${this.BASE_URL}/bookings`, body,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;
    }

    static async getBookingByID(id) {
        const resp = await axios.get(`${this.BASE_URL}/bookings/${id}`,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;
    }

    static async getAllBookings() {
        const resp = await axios.pgetost(`${this.BASE_URL}/bookings`,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;
    }

    static async getCurrentUserBookings() {
        const resp = await axios.post(`${this.BASE_URL}/bookings/me`, body,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;
    }

    static async updateBookingStatus(id, status) {
        const resp = await axios.put(`${this.BASE_URL}/bookings/${id},status`,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;
    }






    //flight api methods

    
static async createFlight(body) {
        const resp = await axios.post(`${this.BASE_URL}/flights`,body,
            {
                headers: this.getHeader()
            }
        );
        return resp.data;}

        
static async getFlightByID(id) {
        const resp = await axios.get(`${this.BASE_URL}/flights/${id}`
        );
        return resp.data;}

        static async getAllflights(id) {
        const resp = await axios.get(`${this.BASE_URL}/flights`
        );
        return resp.data;}


        static async updateFlight(id) {
        const resp = await axios.put(`${this.BASE_URL}/flights}`,body ,
               {
                headers: this.getHeader()
            }
        );
        return resp.data;}

            static async searchFlight(departureIataCode ,arrivalIataCode ,departureDate) {

                const params = {
                    departureIataCode : departureIataCode , 
                    arrivalIataCode : arrivalIataCode , 
                    departureDate : departureDate
                }
        const resp = await axios.post(`${this.BASE_URL}/flights/search` ,{
            params :params
        }
        );
        return resp.data;}

        
        static async getAllCountries(id) {
        const resp = await axios.get(`${this.BASE_URL}/flights/countries`
        );
        return resp.data;}

         static async getAllCities(id) {
        const resp = await axios.get(`${this.BASE_URL}/flights/cities`
        );
        return resp.data;}
        
















}
