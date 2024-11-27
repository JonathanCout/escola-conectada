import React, { useState, useEffect } from 'react';
import { 
  Table, 
  TableBody, 
  TableCell, 
  TableContainer, 
  TableHead, 
  TableRow, 
  Paper,
  Button,
  Typography,
  Box,
  Card,
  CardContent,
  Grid
} from '@mui/material';
import { eventoService } from '../../services/eventoService';
import { format } from 'date-fns';

export const ListaEventos = () => {
  const [eventos, setEventos] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    carregarEventos();
  }, []);

  const carregarEventos = async () => {
    try {
      const data = await eventoService.getEventos();
      setEventos(data);
    } catch (err) {
      setError('Erro ao carregar eventos');
      console.error(err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await eventoService.deleteEvento(id);
      carregarEventos();
    } catch (err) {
      setError('Erro ao deletar evento');
      console.error(err);
    }
  };

  const formatarData = (data) => {
    try {
      return format(new Date(data), 'dd/MM/yyyy HH:mm');
    } catch (err) {
      return 'Data inválida';
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Eventos
      </Typography>
      
      <Button 
        variant="contained" 
        color="primary" 
        sx={{ mb: 2 }}
        onClick={() => {/* Implementar navegação para página de criação */}}
      >
        Novo Evento
      </Button>

      {error && (
        <Typography color="error" sx={{ mb: 2 }}>
          {error}
        </Typography>
      )}

      <Grid container spacing={3}>
        {eventos.map((evento) => (
          <Grid item xs={12} sm={6} md={4} key={evento.id}>
            <Card>
              <CardContent>
                <Typography variant="h6" component="div">
                  {evento.nome}
                </Typography>
                <Typography color="text.secondary">
                  {formatarData(evento.data)}
                </Typography>
                <Typography variant="body2" sx={{ mt: 1 }}>
                  {evento.descricao}
                </Typography>
                <Typography variant="body2" color="text.secondary" sx={{ mt: 1 }}>
                  Local: {evento.local}
                </Typography>
                <Box sx={{ mt: 2, display: 'flex', gap: 1 }}>
                  <Button 
                    size="small" 
                    color="primary"
                    onClick={() => {/* Implementar edição */}}
                  >
                    Editar
                  </Button>
                  <Button 
                    size="small" 
                    color="error"
                    onClick={() => handleDelete(evento.id)}
                  >
                    Excluir
                  </Button>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};