import api from './api';

export const discplinaService = {
  getDisciplinas: async () => {
    try {
      await api.get('/disciplinas/list');
    } catch (error) {
      console.error('Erro ao buscar disciplinas:', error);
      throw error;
    }
  },

  getDiscplinaById: async (id) => {
    try {
      const response = await api.get(`/discplinas/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar discplina:', error);
      throw error;
    }
  },

  createDiscplina: async (discplina) => {
    try {
      const response = await api.post('/discplinas', discplina);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar discplina:', error);
      throw error;
    }
  },

  deleteDiscplina: async (id) => {
    try {
      await api.delete(`/discplinas/${id}`);
    } catch (error) {
      console.error('Erro ao deletar discplina:', error);
      throw error;
    }
  },
};