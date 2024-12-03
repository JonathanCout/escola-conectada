import api from './api';

export const disciplinaService = {
  getDisciplinas: async () => {
    try {
      const response = await api.get('/disciplinas/list');
      return response.data
    } catch (error) {
      console.error('Erro ao buscar disciplinas:', error);
      throw error;
    }
  },

  getDisciplinaById: async (id) => {
    try {
      const response = await api.get(`/disciplinas/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar disciplina:', error);
      throw error;
    }
  },

  createDisciplina: async (disciplina) => {
    try {
      const response = await api.post('/disciplinas', disciplina);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar disciplina:', error);
      throw error;
    }
  },

  deleteDisciplina: async (id) => {
    try {
      await api.delete(`/disciplinas/${id}`);
    } catch (error) {
      console.error('Erro ao deletar disciplina:', error);
      throw error;
    }
  },
};