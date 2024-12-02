import api from './api';

export const merendaService = {
  getMerendas: async () => {
    try {
      const response = await api.get('/merendas/list');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar merendas:', error);
      throw error;
    }
  },

  getMerendaById: async (id) => {
    try {
      const response = await api.get(`/merendas/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar merenda:', error);
      throw error;
    }
  },

  createMerenda: async (merenda) => {
    try {
      const response = await api.post('/merendas', merenda);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar merenda:', error);
      throw error;
    }
  },

  updateMerenda: async (id, merenda) => {
    try {
      const response = await api.put(`/merendas/${id}`, merenda);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar merenda:', error);
      throw error;
    }
  },

  deleteMerenda: async (id) => {
    try {
      await api.delete(`/merendas/${id}`);
    } catch (error) {
      console.error('Erro ao deletar merenda:', error);
      throw error;
    }
  }
};