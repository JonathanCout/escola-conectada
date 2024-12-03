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
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Divider,
  Grid,
  Card,
  CardContent
} from '@mui/material';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { turmaService } from '../../services/turmaService';
import { useNavigate } from 'react-router-dom';

export const ListaTurmas = () => {
  const [turmas, setTurmas] = useState([]);
  const [error, setError] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [turmaSelecionada, setTurmaSelecionada] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    carregarTurmas();
  }, []);

  const carregarTurmas = async () => {
    setLoading(true);
    try {
      const data = await turmaService.getTurmas();
      setTurmas(data);
      setError(null);
    } catch (err) {
      setError('Erro ao carregar turmas');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const carregarTurma = async (id) => {
    setLoading(true);
    try {
      const data = await turmaService.getTurmaById(id);
      setTurmaSelecionada(data);
    } catch (err) {
      setError('Erro ao carregar turma');
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
      setError('Erro ao excluir turma');
      console.error(err);
    }
  };

  const handleOpenModal = (turma) => {
    carregarTurma(turma.id);
    setOpenModal(true);
  };

  const handleCloseModal = () => {
    setTurmaSelecionada(null);
    setOpenModal(false);
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

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Código</TableCell>
              <TableCell>Período</TableCell>
              <TableCell>Horário</TableCell>
              <TableCell>Professor</TableCell>
              <TableCell>Disciplina</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {turmas.map((turma) => (
              <TableRow key={turma.id}>
                <TableCell>{turma.codigo}</TableCell>
                <TableCell>{turma.periodo}</TableCell>
                <TableCell>{`${turma.horario?.hour}:${turma.horario?.minute}:${turma.horario?.second}`}</TableCell>
                <TableCell>{turma.professor?.nome || 'N/A'}</TableCell>
                <TableCell>{turma.disciplina?.nome || 'N/A'}</TableCell>
                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={() => handleOpenModal(turma)}
                  >
                    <VisibilityIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Modal de Observações */}
      <Dialog open={openModal} onClose={handleCloseModal} maxWidth="md" fullWidth>
        <DialogTitle>Informações da Turma</DialogTitle>
        <DialogContent>
          {loading ? (
            <Typography>Carregando dados...</Typography>
          ) : (
            turmaSelecionada && (
              <Card variant="outlined" sx={{ p: 3, borderRadius: 2 }}>
                <CardContent>
                  <Typography variant="h6" gutterBottom>
                    Detalhes da Turma
                  </Typography>
                  <Grid container spacing={2}>
                    <Grid item xs={6}>
                      <Typography><strong>Código:</strong> {turmaSelecionada.codigo}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Período:</strong> {turmaSelecionada.periodo}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Horário:</strong> {`${turmaSelecionada.horario?.hour}:${turmaSelecionada.horario?.minute}:${turmaSelecionada.horario?.second}`}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Professor:</strong> {turmaSelecionada.professor?.nome || 'N/A'}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Disciplina:</strong> {turmaSelecionada.disciplina?.nome || 'N/A'}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Alunos:</strong> {turmaSelecionada.alunosId?.join(', ') || 'N/A'}</Typography>
                    </Grid>
                  </Grid>

                  <Divider sx={{ my: 3 }} />

                  <Typography variant="h6" gutterBottom>
                    Observações
                  </Typography>
                  <Typography>{turmaSelecionada.observacoes || 'Nenhuma observação encontrada.'}</Typography>
                </CardContent>
              </Card>
            )
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseModal} color="secondary">Fechar</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};