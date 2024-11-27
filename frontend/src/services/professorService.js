import api from './api';

export const professorService = {
  getProfessores: async () => {
    try {
      const response = await api.get('/professores');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar professores:', error);
      throw error;
    }
  },

  getProfessorById: async (id) => {
    try {
      const response = await api.get(`/professores/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar professor:', error);
      throw error;
    }
  },

  createProfessor: async (professor) => {
    try {
      const response = await api.post('/professores', professor);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar professor:', error);
      throw error;
    }
  },

  updateProfessor: async (id, professor) => {
    try {
      const response = await api.put(`/professores/${id}`, professor);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar professor:', error);
      throw error;
    }
  },

  deleteProfessor: async (id) => {
    try {
      await api.delete(`/professores/${id}`);
    } catch (error) {
      console.error('Erro ao deletar professor:', error);
      throw error;
    }
  },
};