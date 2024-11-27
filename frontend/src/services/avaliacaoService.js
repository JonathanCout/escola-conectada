import api from './api';

export const avaliacaoService = {
  getAvaliacoes: async () => {
    try {
      const response = await api.get('/avaliacoes');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar avaliações:', error);
      throw error;
    }
  },

  getAvaliacaoById: async (id) => {
    try {
      const response = await api.get(`/avaliacoes/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar avaliação:', error);
      throw error;
    }
  },

  createAvaliacao: async (avaliacao) => {
    try {
      const response = await api.post('/avaliacoes', avaliacao);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar avaliação:', error);
      throw error;
    }
  },

  updateAvaliacao: async (id, avaliacao) => {
    try {
      const response = await api.put(`/avaliacoes/${id}`, avaliacao);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar avaliação:', error);
      throw error;
    }
  },

  deleteAvaliacao: async (id) => {
    try {
      await api.delete(`/avaliacoes/${id}`);
    } catch (error) {
      console.error('Erro ao deletar avaliação:', error);
      throw error;
    }
  }
};