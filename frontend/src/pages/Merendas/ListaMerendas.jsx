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
  Grid,
  Chip
} from '@mui/material';
import { merendaService } from '../../services/merendaService';
import RestaurantIcon from '@mui/icons-material/Restaurant';
import WbTwilightIcon from '@mui/icons-material/WbTwilight';
import WbSunnyIcon from '@mui/icons-material/WbSunny';
import NightsStayIcon from '@mui/icons-material/NightsStay';

export const ListaMerendas = () => {
  const [merendas, setMerendas] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    carregarMerendas();
  }, []);

  const carregarMerendas = async () => {
    try {
      const data = await merendaService.getMerendas();
      setMerendas(data);
    } catch (err) {
      setError('Erro ao carregar merendas');
      console.error(err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await merendaService.deleteMerenda(id);
      carregarMerendas();
    } catch (err) {
      setError('Erro ao deletar merenda');
      console.error(err);
    }
  };

  const getTurnoIcon = (turno) => {
    switch (turno) {
      case 'MANHA':
        return <WbSunnyIcon />;
      case 'TARDE':
        return <WbTwilightIcon />;
      case 'NOITE':
        return <NightsStayIcon />;
      default:
        return <RestaurantIcon />;
    }
  };

  const getTurnoColor = (turno) => {
    switch (turno) {
      case 'MANHA':
        return 'primary';
      case 'TARDE':
        return 'warning';
      case 'NOITE':
        return 'secondary';
      default:
        return 'default';
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Cardápio da Merenda
      </Typography>
      
      <Button 
        variant="contained" 
        color="primary" 
        sx={{ mb: 2 }}
        startIcon={<RestaurantIcon />}
        onClick={() => {/* Implementar navegação para página de criação */}}
      >
        Nova Merenda
      </Button>

      {error && (
        <Typography color="error" sx={{ mb: 2 }}>
          {error}
        </Typography>
      )}

      <Grid container spacing={3}>
        {merendas.map((merenda) => (
          <Grid item xs={12} sm={6} md={4} key={merenda.id}>
            <Card>
              <CardContent>
                <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                  <Typography variant="h6" component="div">
                    {merenda.nome}
                  </Typography>
                  <Chip 
                    icon={getTurnoIcon(merenda.turno)}
                    label={merenda.turno}
                    color={getTurnoColor(merenda.turno)}
                    size="small"
                  />
                </Box>
                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                  {merenda.descricao}
                </Typography>
                <Box sx={{ display: 'flex', gap: 1 }}>
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
                    onClick={() => handleDelete(merenda.id)}
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