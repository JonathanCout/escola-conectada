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
import { useNavigate } from 'react-router-dom';
import { alunoService } from '../../services/alunoService';

export const ListaAlunos = () => {
  const [alunos, setAlunos] = useState([]);
  const [error, setError] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [alunoSelecionado, setAlunoSelecionado] = useState(null);
  const [loading, setLoading] = useState(false); // Controle de carregamento
  const navigate = useNavigate();

  useEffect(() => {
    carregarAlunos();
  }, []);

  const carregarAlunos = async () => {
    try {
      const data = await alunoService.getAlunos();
      console.log(data);
      setAlunos(data);
    } catch (err) {
      setError('Erro ao carregar alunos');
      console.error(err);
    }
  };

  const carregarAluno = async (id) => {
    setLoading(true);  // Inicia o carregamento
    try {
      const data = await alunoService.getAlunoById(id);
      setAlunoSelecionado(data); // Atualiza o aluno selecionado
    } catch (err) {
      setError('Erro ao carregar aluno');
      console.error(err);
    } finally {
      setLoading(false);  // Finaliza o carregamento
    }
  };

  const handleDelete = async (id) => {
    try {
      await alunoService.deleteAluno(id);
      carregarAlunos(); // Recarrega a lista após deletar
    } catch (err) {
      setError('Erro ao deletar aluno');
      console.error(err);
    }
  };

  const handleOpenModal = (aluno) => {
    carregarAluno(aluno.id);  // Carrega o aluno ao abrir o modal
    setOpenModal(true);
  };

  const handleCloseModal = () => {
    setAlunoSelecionado(null);
    setOpenModal(false);
  };

  useEffect(() => {
    if (alunoSelecionado) {
      console.log(alunoSelecionado.responsaveis); // Verifica os responsáveis carregados
    }
  }, [alunoSelecionado]);  // Acompanha as mudanças de alunoSelecionado

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>
        Lista de Alunos
      </Typography>

      <Button
        variant="contained"
        color="primary"
        sx={{ mb: 2 }}
        onClick={() => navigate('/alunos/novo')}
      >
        Novo Aluno
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
              <TableCell>Observações</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {alunos.map((aluno) => (
              <TableRow key={aluno.id}>
                <TableCell>{aluno.matricula}</TableCell>
                <TableCell>{aluno.nome}</TableCell>
                <TableCell>{aluno.email}</TableCell>
                <TableCell>{aluno.telefone}</TableCell>
                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={() => handleOpenModal(aluno)}
                  >
                    <VisibilityIcon />
                  </IconButton>
                </TableCell>
                <TableCell>
                  <Button
                    color="primary"
                    onClick={() => navigate(`/alunos/editar/${aluno.id}`)} // Navega para a página de edição passando o ID do aluno
                  >
                    Editar
                  </Button>
                  <Button
                    color="error"
                    onClick={() => handleDelete(aluno.id)}
                  >
                    Excluir
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* Modal de Observações */}
      <Dialog open={openModal} onClose={handleCloseModal} maxWidth="md" fullWidth>
        <DialogTitle>Informações do Aluno</DialogTitle>
        <DialogContent>
          {loading ? (
            <Typography>Carregando dados...</Typography>  // Exibe mensagem enquanto os dados estão carregando
          ) : (
            alunoSelecionado && (
              <Card variant="outlined" sx={{ p: 3, borderRadius: 2 }}>
                <CardContent>
                  <Typography variant="h6" gutterBottom>
                    Informações Pessoais
                  </Typography>
                  <Grid container spacing={2}>
                    <Grid item xs={6}>
                      <Typography><strong>Nome:</strong> {alunoSelecionado.nome}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>CPF:</strong> {alunoSelecionado.cpf}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Email:</strong> {alunoSelecionado.email}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Telefone:</strong> {alunoSelecionado.telefone}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Matrícula:</strong> {alunoSelecionado.matricula}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography><strong>Ano:</strong> {alunoSelecionado.ano}</Typography>
                    </Grid>
                  </Grid>

                  <Divider sx={{ my: 3 }} />

                  <Typography variant="h6" gutterBottom>
                    Endereço
                  </Typography>
                  <Typography><strong>CEP:</strong> {alunoSelecionado.endereco.cep}</Typography>
                  <Typography><strong>Logradouro:</strong> {alunoSelecionado.endereco.logradouro}</Typography>
                  <Typography><strong>Número:</strong> {alunoSelecionado.endereco.numero}</Typography>
                  <Typography><strong>Complemento:</strong> {alunoSelecionado.endereco.complemento}</Typography>
                  <Typography><strong>Bairro:</strong> {alunoSelecionado.endereco.bairro}</Typography>
                  <Typography><strong>Cidade:</strong> {alunoSelecionado.endereco.cidade}</Typography>
                  <Typography><strong>Estado:</strong> {alunoSelecionado.endereco.estado}</Typography>

                  <Divider sx={{ my: 3 }} />

                  <Typography variant="h6" gutterBottom>
                    Responsáveis
                  </Typography>
                  {alunoSelecionado?.responsaveis?.length > 0 ? (
                    alunoSelecionado.responsaveis.map((responsavel, index) => (
                      <Box key={index} sx={{ mb: 2, p: 2, border: '1px solid #ddd', borderRadius: 2 }}>
                        <Typography><strong>Nome:</strong> {responsavel.nome}</Typography>
                        <Typography><strong>CPF:</strong> {responsavel.cpf}</Typography>
                        <Typography><strong>Email:</strong> {responsavel.email}</Typography>
                        <Typography><strong>Telefone:</strong> {responsavel.telefone}</Typography>
                      </Box>
                    ))
                  ) : (
                    <Typography>Nenhum responsável encontrado.</Typography>
                  )}
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