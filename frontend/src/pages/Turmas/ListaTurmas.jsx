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
  Box
} from '@mui/material';
import { turmaService } from '../../services/turmaService';
import { useNavigate } from 'react-router-dom';

export const ListaTurmas = () => {
  const [turmas, setTurmas] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    carregarTurmas();
  }, []);

  const carregarTurmas = async () => {
    try {
      const data = await turmaService.getTurmas();
      setTurmas(data);
    } catch (err) {
      setError('Erro ao carregar turmas');
      console.error(err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await turmaService.deleteTurma(id);
      carregarTurmas(); // Recarrega a lista após deletar
    } catch (err) {
      setError('Erro ao deletar turma');
      console.error(err);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Lista de Turmas
      </Typography>
      
      <Button 
        variant="contained" 
        color="primary" 
        sx={{ mb: 2 }}
        onClick={() => navigate('/Turmas/NovaTurma')/* Implementar navegação para página de criação */}
      >
        Nova Turma
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
              <TableCell>Código</TableCell>
              <TableCell>Nome</TableCell>
              <TableCell>Ano</TableCell>
              <TableCell>Professor</TableCell>
              <TableCell>Disciplina</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {turmas.map((turma) => (
              <TableRow key={turma.id}>
                <TableCell>{turma.codigo}</TableCell>
                <TableCell>{turma.nome}</TableCell>
                <TableCell>{turma.ano}</TableCell>
                <TableCell>{turma.professor?.nome}</TableCell>
                <TableCell>{turma.disciplina?.nome}</TableCell>
                <TableCell>
                  <Button 
                    color="primary"
                    onClick={() => {/* Implementar edição */}}
                  >
                    Editar
                  </Button>
                  <Button 
                    color="error"
                    onClick={() => handleDelete(turma.id)}
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