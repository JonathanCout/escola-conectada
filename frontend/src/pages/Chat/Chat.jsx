// frontend/src/pages/Chat/Chat.jsx
import React, { useState, useEffect } from 'react';
import { Box, Typography, Button, TextField, List, ListItem, ListItemText, Paper } from '@mui/material';
import { chatService } from '../../services/chatService';
import { useParams } from 'react-router-dom';

export const Chat = () => {
  const { conversaId } = useParams(); // Supondo que você está passando o ID da conversa pela URL
  const [mensagens, setMensagens] = useState([]);
  const [novaMensagem, setNovaMensagem] = useState('');
  const [error, setError] = useState(null);
  const usuarioId = 1; // Substitua pelo ID do usuário logado

  useEffect(() => {
    carregarMensagens();
  }, [conversaId]);

  const carregarMensagens = async () => {
    try {
      const data = await chatService.getMensagens(conversaId);
      setMensagens(data);
    } catch (err) {
      setError('Erro ao carregar mensagens');
      console.error(err);
    }
  };

  const handleEnviarMensagem = async () => {
    if (!novaMensagem.trim()) return;

    const mensagemRequest = {
      texto: novaMensagem,
      usuarioId: usuarioId, // ID do usuário que está enviando a mensagem
    };

    try {
      const novaMensagemResponse = await chatService.enviarMensagem(conversaId, mensagemRequest);
      setMensagens([...mensagens, novaMensagemResponse]);
      setNovaMensagem('');
    } catch (err) {
      setError('Erro ao enviar mensagem');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Paper sx={{ p: 3 }}>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Chat
        </Typography>

        {error && <Typography color="error">{error}</Typography>}

        <List>
          {mensagens.map((mensagem) => (
            <ListItem key={mensagem.id}>
              <ListItemText primary={mensagem.texto} secondary={`Enviado por: ${mensagem.usuarioId}`} />
            </ListItem>
          ))}
        </List>

        <TextField
          label="Nova Mensagem"
          fullWidth
          value={novaMensagem}
          onChange={(e) => setNovaMensagem(e.target.value)}
          onKeyPress={(e) => e.key === 'Enter' && handleEnviarMensagem()}
        />
        <Button variant="contained" color="primary" onClick={handleEnviarMensagem} sx={{ mt: 2 }}>
          Enviar
        </Button>
      </Paper>
    </Box>
  );
};

export default Chat;