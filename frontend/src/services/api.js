import axios from 'axios';
import { authService } from './authService'; // Importa o authService para pegar o token

const api = axios.create({
  baseURL: 'http://localhost:8082/escola-conectada',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Adiciona o token de autenticação às requisições sempre que elas forem feitas
api.interceptors.request.use(
  (config) => {
    const token = authService.getToken(); // Obtém o token do authService
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`; // Adiciona o token ao header
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
