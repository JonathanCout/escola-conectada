import api from './api';

export const eventoService = {
  getEventos: async () => {
    try {
      const response = await api.get('/eventos/list');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar eventos:', error);
      throw error;
    }
  },

  getEventoById: async (id) => {
    try {
      const response = await api.get(`/eventos/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar evento:', error);
      throw error;
    }
  },

  createEvento: async (evento) => {
    try {
      const response = await api.post('/eventos', evento);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar evento:', error);
      throw error;
    }
  },

  updateEvento: async (id, evento) => {
    try {
      const response = await api.put(`/eventos/${id}`, evento);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar evento:', error);
      throw error;
    }
  },

  deleteEvento: async (id) => {
    try {
      await api.delete(`/eventos/${id}`);
    } catch (error) {
      console.error('Erro ao deletar evento:', error);
      throw error;
    }
  }
};