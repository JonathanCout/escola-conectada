import api from './api';

const TOKEN_KEY = 'auth_token';

export const authService = {
  login: async (email, senha) => {
    try {
      // Simula uma chamada à API
      // Na implementação real, isso deve ser uma chamada POST para o seu backend
      const response = await new Promise(resolve => 
        setTimeout(() => resolve({ data: { token: 'fake_token', user: { nome: 'Usuário Teste', email } } }), 1000)
      );

      const { token, user } = response.data;
      localStorage.setItem(TOKEN_KEY, token);
      localStorage.setItem('user', JSON.stringify(user));
      
      return user;
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      throw error;
    }
  },

  logout: () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem('user');
  },

  getToken: () => localStorage.getItem(TOKEN_KEY),

  isAuthenticated: () => !!localStorage.getItem(TOKEN_KEY),

  getUser: () => {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
  }
};