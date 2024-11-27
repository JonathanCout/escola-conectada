import api from './api';

export const conversaService = {
  getConversas: async () => {
    try {
      const response = await api.get('/conversas');
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar conversas:', error);
      throw error;
    }
  },

  getConversaById: async (id) => {
    try {
      const response = await api.get(`/conversas/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar conversa:', error);
      throw error;
    }
  },

  createConversa: async (conversa) => {
    try {
      const response = await api.post('/conversas', conversa);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar conversa:', error);
      throw error;
    }
  },

  updateConversa: async (id, conversa) => {
    try {
      const response = await api.put(`/conversas/${id}`, conversa);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar conversa:', error);
      throw error;
    }
  },

  deleteConversa: async (id) => {
    try {
      await api.delete(`/conversas/${id}`);
    } catch (error) {
      console.error('Erro ao deletar conversa:', error);
      throw error;
    }
  },

  enviarMensagem: async (conversaId, mensagem) => {
    try {
      const response = await api.post(`/conversas/${conversaId}/mensagens`, mensagem);
      return response.data;
    } catch (error) {
      console.error('Erro ao enviar mensagem:', error);
      throw error;
    }
  }
};