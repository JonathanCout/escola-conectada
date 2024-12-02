import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
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
import { professorService } from '../../services/professorService';

export const ListaProfessores = () => {
  const [professores, setProfessores] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    carregarProfessores();
  }, []);

  const carregarProfessores = async () => {
    setLoading(true);
    try {
      const data = await professorService.getProfessores();
      setProfessores(Array.isArray(data) ? data : []);
      setError(null);
    } catch (err) {
      setError('Erro ao carregar professores');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    try {
      await professorService.deleteProfessor(id);
      carregarProfessores(); // Recarrega a lista após deletar
    } catch (err) {
      setError('Erro ao deletar professor');
      console.error(err);
    }
  };

  const handleEdit = (id) => {
    navigate(`/professores/editar/${id}`); // Navega para a página de edição
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Lista de Professores
      </Typography>
      
      <Button 
        variant="contained" 
        color="primary" 
        sx={{ mb: 2 }}
        onClick={() => {navigate('/professores/novo')}}
      >
        Novo Professor
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
              <TableCell>Matrícula</TableCell>
              <TableCell>Nome</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Telefone</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {professores.map((professor) => (
              <TableRow key={professor.id}>
                <TableCell>{professor.matricula}</TableCell>
                <TableCell>{professor.nome}</TableCell>
                <TableCell>{professor.email}</TableCell>
                <TableCell>{professor.telefone}</TableCell>
                <TableCell>
                  <Button 
                    color="primary"
                    onClick={() => {handleEdit(professor.id)}}
                  >
                    Editar
                  </Button>
                  <Button 
                    color="error"
                    onClick={() => handleDelete(professor.id)}
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