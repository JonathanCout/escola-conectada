// frontend/src/services/chatService.js
import api from './api';

export const chatService = {
  getConversas: async (usuarioId) => {
    try {
      const response = await api.get(`/chat/conversas/${usuarioId}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar conversas:', error);
      throw error;
    }
  },

  getMensagens: async (conversaId) => {
    try {
      const response = await api.get(`/chat/conversas/${conversaId}/mensagens`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar mensagens:', error);
      throw error;
    }
  },

  enviarMensagem: async (conversaId, mensagem) => {
    try {
      const response = await api.post(`/chat/conversas/${conversaId}/mensagens`, mensagem);
      return response.data;
    } catch (error) {
      console.error('Erro ao enviar mensagem:', error);
      throw error;
    }
  }
};