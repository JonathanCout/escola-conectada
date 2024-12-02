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
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    carregarTurmas();
  }, []);

  const carregarTurmas = async () => {
    setLoading(true);
    try {
      const data = await turmaService.getTurmas();
      setTurmas(Array.isArray(data) ? data : []); // Garante que seja sempre um array
      setError(null); // Limpa erros anteriores, se existirem
    } catch (err) {
      setError('Erro ao carregar turmas. Tente novamente mais tarde.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    try {
      await turmaService.deleteTurma(id);
      carregarTurmas(); // Recarrega a lista após deletar
    } catch (err) {
      setError(`Erro ao excluir a turma com ID ${id}. Tente novamente.`);
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
        onClick={() => navigate('/turmas/novaturma')}
      >
        Nova Turma
      </Button>

      {error && (
        <Typography color="error" sx={{ mb: 2 }}>
          {error}
        </Typography>
      )}

      {loading ? (
        <Typography align="center" sx={{ mt: 3 }}>
          Carregando...
        </Typography>
      ) : (
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
              {Array.isArray(turmas) && turmas.length > 0 ? (
                turmas.map((turma) => (
                  <TableRow key={turma.id}>
                    <TableCell>{turma.codigo}</TableCell>
                    <TableCell>{turma.nome}</TableCell>
                    <TableCell>{turma.ano}</TableCell>
                    <TableCell>{turma.professor?.nome || 'N/A'}</TableCell>
                    <TableCell>{turma.disciplina?.nome || 'N/A'}</TableCell>
                    <TableCell>
                      <Button 
                        color="primary"
                        onClick={() => navigate(`/turmas/editar/${turma.id}`)}
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
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={6} align="center">
                    Nenhuma turma encontrada.
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      )}
    </Box>
  );
};