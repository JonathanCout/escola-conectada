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
  Chip
} from '@mui/material';
import { avaliacaoService } from '../../services/avaliacaoService';
import AssignmentIcon from '@mui/icons-material/Assignment';

export const ListaAvaliacoes = () => {
  const [avaliacoes, setAvaliacoes] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    carregarAvaliacoes();
  }, []);

  const carregarAvaliacoes = async () => {
    try {
      const data = await avaliacaoService.getAvaliacoes();
      setAvaliacoes(data);
    } catch (err) {
      setError('Erro ao carregar avaliações');
      console.error(err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await avaliacaoService.deleteAvaliacao(id);
      carregarAvaliacoes();
    } catch (err) {
      setError('Erro ao deletar avaliação');
      console.error(err);
    }
  };

  const getNotaColor = (nota) => {
    if (nota >= 8) return 'success';
    if (nota >= 6) return 'warning';
    return 'error';
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Avaliações
      </Typography>
      
      <Button 
        variant="contained" 
        color="primary" 
        sx={{ mb: 2 }}
        startIcon={<AssignmentIcon />}
        onClick={() => {/* Implementar navegação para página de criação */}}
      >
        Nova Avaliação
      </Button>

      {error && (
        <Typography color="error" sx={{ mb: 2 }}>
          {error}
        </Typography>
      )}

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Aluno</TableCell>
              <TableCell>Turma</TableCell>
              <TableCell>Tipo</TableCell>
              <TableCell>Nota</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {avaliacoes.map((avaliacao) => (
              <TableRow key={avaliacao.id}>
                <TableCell>{avaliacao.aluno.nome}</TableCell>
                <TableCell>{avaliacao.turma.nome}</TableCell>
                <TableCell>{avaliacao.tipo}</TableCell>
                <TableCell>
                  <Chip 
                    label={avaliacao.nota.toFixed(2)} 
                    color={getNotaColor(avaliacao.nota)}
                  />
                </TableCell>
                <TableCell>
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
                    onClick={() => handleDelete(avaliacao.id)}
                  >
                    Excluir
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};