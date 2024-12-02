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

// Trata erros globais nas respostas
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      authService.logout(); // Remove o token inválido
      window.location.href = '/login'; // Redireciona para a página de login
    }
    return Promise.reject(error);
  }
);

console.log('API Configurada:', api);
export default api;