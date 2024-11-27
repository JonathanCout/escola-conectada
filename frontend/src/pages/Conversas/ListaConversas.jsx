import React, { useState, useEffect } from 'react';
import { 
  List,
  ListItem,
  ListItemText,
  ListItemAvatar,
  Avatar,
  Typography,
  Box,
  Paper,
  Divider,
  Button,
  TextField,
  Grid
} from '@mui/material';
import { conversaService } from '../../services/conversaService';
import SendIcon from '@mui/icons-material/Send';

export const ListaConversas = () => {
  const [conversas, setConversas] = useState([]);
  const [selectedConversa, setSelectedConversa] = useState(null);
  const [novaMensagem, setNovaMensagem] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    carregarConversas();
  }, []);

  const carregarConversas = async () => {
    try {
      const data = await conversaService.getConversas();
      setConversas(data);
    } catch (err) {
      setError('Erro ao carregar conversas');
      console.error(err);
    }
  };

  const handleSelectConversa = async (id) => {
    try {
      const conversa = await conversaService.getConversaById(id);
      setSelectedConversa(conversa);
    } catch (err) {
      setError('Erro ao carregar conversa');
      console.error(err);
    }
  };

  const handleEnviarMensagem = async () => {
    if (!novaMensagem.trim() || !selectedConversa) return;

    try {
      const mensagem = {
        conteudo: novaMensagem,
        remetenteId: 1, // Isso deve ser o ID do usuário logado
        dataEnvio: new Date().toISOString()
      };
      await conversaService.enviarMensagem(selectedConversa.id, mensagem);
      setNovaMensagem('');
      handleSelectConversa(selectedConversa.id);
    } catch (err) {
      setError('Erro ao enviar mensagem');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Conversas
      </Typography>

      {error && (
        <Typography color="error" sx={{ mb: 2 }}>
          {error}
        </Typography>
      )}

      <Grid container spacing={2}>
        <Grid item xs={12} md={4}>
          <Paper elevation={3}>
            <List sx={{ bgcolor: 'background.paper' }}>
              {conversas.map((conversa) => (
                <React.Fragment key={conversa.id}>
                  <ListItem 
                    button 
                    onClick={() => handleSelectConversa(conversa.id)}
                    selected={selectedConversa && selectedConversa.id === conversa.id}
                  >
                    <ListItemAvatar>
                      <Avatar>{conversa.aluno.nome[0]}</Avatar>
                    </ListItemAvatar>
                    <ListItemText 
                      primary={conversa.aluno.nome} 
                      secondary={`Prof. ${conversa.professor.nome}`} 
                    />
                  </ListItem>
                  <Divider variant="inset" component="li" />
                </React.Fragment>
              ))}
            </List>
          </Paper>
        </Grid>
        <Grid item xs={12} md={8}>
          <Paper elevation={3} sx={{ p: 2, height: '70vh', display: 'flex', flexDirection: 'column' }}>
            {selectedConversa ? (
              <>
                <Box sx={{ flexGrow: 1, overflowY: 'auto', mb: 2 }}>
                  {selectedConversa.mensagens.map((mensagem) => (
                    <Box 
                      key={mensagem.id} 
                      sx={{ 
                        mb: 1, 
                        p: 1, 
                        bgcolor: mensagem.remetenteId === 1 ? 'primary.light' : 'grey.200',
                        borderRadius: 1,
                        maxWidth: '70%',
                        alignSelf: mensagem.remetenteId === 1 ? 'flex-end' : 'flex-start'
                      }}
                    >
                      <Typography variant="body2">{mensagem.conteudo}</Typography>
                      <Typography variant="caption" sx={{ display: 'block', mt: 0.5 }}>
                        {new Date(mensagem.dataEnvio).toLocaleString()}
                      </Typography>
                    </Box>
                  ))}
                </Box>
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                  <TextField
                    fullWidth
                    variant="outlined"
                    placeholder="Digite sua mensagem..."
                    value={novaMensagem}
                    onChange={(e) => setNovaMensagem(e.target.value)}
                    onKeyPress={(e) => e.key === 'Enter' && handleEnviarMensagem()}
                  />
                  <Button
                    variant="contained"
                    color="primary"
                    endIcon={<SendIcon />}
                    onClick={handleEnviarMensagem}
                    sx={{ ml: 1 }}
                  >
                    Enviar
                  </Button>
                </Box>
              </>
            ) : (
              <Typography variant="body1" sx={{ textAlign: 'center', mt: 2 }}> Selecione uma conversa</Typography>
            )}
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};