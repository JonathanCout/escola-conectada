import api from './api';

export const turmaService = {
  getTurmas: async () => {
    try {
      const response = await api.get('/turmas');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar turmas:', error);
      throw error;
    }
  },

  getTurmaById: async (id) => {
    try {
      const response = await api.get(`/turmas/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar turma:', error);
      throw error;
    }
  },

  createTurma: async (turma) => {
    try {
      const response = await api.post('/turmas', turma);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar turma:', error);
      throw error;
    }
  },

  updateTurma: async (id, turma) => {
    try {
      const response = await api.put(`/turmas/${id}`, turma);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar turma:', error);
      throw error;
    }
  },

  deleteTurma: async (id) => {
    try {
      await api.delete(`/turmas/${id}`);
    } catch (error) {
      console.error('Erro ao deletar turma:', error);
      throw error;
    }
  },
};