import api from './api';

export const alunoService = {
  getAlunos: async () => {
    try {
      const response = await api.get('/alunos/list');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar alunos:', error);
      throw error;
    }
  },

  getAlunoById: async (id) => {
    try {
      const response = await api.get(`/alunos/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar aluno:', error);
      throw error;
    }
  },

  createAluno: async (aluno) => {
    try {
      const response = await api.post('/alunos', aluno);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar aluno:', error);
      throw error;
    }
  },

  updateAluno: async (id, aluno) => {
    try {
      const response = await api.put(`/alunos/${id}`, aluno);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar aluno:', error);
      throw error;
    }
  },

  deleteAluno: async (id) => {
    try {
      await api.delete(`/alunos/${id}`);
    } catch (error) {
      console.error('Erro ao deletar aluno:', error);
      throw error;
    }
  },
};