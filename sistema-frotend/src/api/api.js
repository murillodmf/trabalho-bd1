import axios from 'axios';

const api = axios.create({

    // Define a URL base para todas as requisições.
    baseURL: 'http://localhost:8080'
});

export default api;