import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  TextField,
  Button,
  MenuItem,
  CircularProgress,
  Snackbar,
  Alert,
} from '@mui/material';
import { turmaService } from '../../services/turmaService';
import { useNavigate } from 'react-router-dom';

export const NovaTurma = () => {
  const [formData, setFormData] = useState({
    codigo: '',
    nome: '',
    ano: '',
    professor: '',
    disciplina: '',
  });
  const [professores, setProfessores] = useState([]);
  const [disciplinas, setDisciplinas] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    carregarDados();
  }, []);

  const carregarDados = async () => {
    setLoading(true);
    try {
      const [professoresResponse, disciplinasResponse] = await Promise.all([
        turmaService.getProfessores(),
        turmaService.getDisciplinas(),
      ]);
      setProfessores(professoresResponse);
      setDisciplinas(disciplinasResponse);
    } catch (err) {
      setError('Erro ao carregar dados de professores e disciplinas.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setSuccess(false);
    setLoading(true);

    try {
      await turmaService.createTurma(formData);
      setSuccess(true);
      setFormData({
        codigo: '',
        nome: '',
        ano: '',
        professor: '',
        disciplina: '',
      });
    } catch (err) {
      setError('Erro ao criar turma. Verifique os dados e tente novamente.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box sx={{ p: 3, maxWidth: 600, mx: 'auto' }}>
      <Box sx={{ p: 3, backgroundColor: '#fff', borderRadius: 2, boxShadow: 1 }}>
        <Typography variant="h4" sx={{ mb: 3, textAlign: 'center' }}>
          Criar Nova Turma
        </Typography>

        {error && (
          <Alert severity="error" sx={{ mb: 2 }}>
            {error}
          </Alert>
        )}

        <form onSubmit={handleSubmit}>
          <TextField
            label="Código"
            name="codigo"
            value={formData.codigo}
            onChange={handleChange}
            fullWidth
            required
            sx={{ mb: 2 }}
            inputProps={{ maxLength: 10 }}
          />
          <TextField
            label="Nome"
            name="nome"
            value={formData.nome}
            onChange={handleChange}
            fullWidth
            required
            sx={{ mb: 2 }}
          />
          <TextField
            label="Ano"
            name="ano"
            type="number"
            value={formData.ano}
            onChange={handleChange}
            fullWidth
            required
            sx={{ mb: 2 }}
            inputProps={{ min: 2000, max: 2100 }}
          />
          <TextField
            label="Professor"
            name="professor"
            value={formData.professor}
            onChange={handleChange}
            fullWidth
            required
            select
            sx={{ mb: 2 }}
          >
            {professores.length > 0 ? (
              professores.map((professor) => (
                <MenuItem key={professor.id} value={professor.id}>
                  {professor.nome}
                </MenuItem>
              ))
            ) : (
              <MenuItem disabled>Carregando professores...</MenuItem>
            )}
          </TextField>
          <TextField
            label="Disciplina"
            name="disciplina"
            value={formData.disciplina}
            onChange={handleChange}
            fullWidth
            required
            select
            sx={{ mb: 2 }}
          >
            {disciplinas.length > 0 ? (
              disciplinas.map((disciplina) => (
                <MenuItem key={disciplina.id} value={disciplina.id}>
                  {disciplina.nome}
                </MenuItem>
              ))
            ) : (
              <MenuItem disabled>Carregando disciplinas...</MenuItem>
            )}
          </TextField>

          <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 3 }}>
            <Button
              type="submit"
              variant="contained"
              color="primary"
              disabled={loading}
              startIcon={loading && <CircularProgress size={20} />}
            >
              Salvar
            </Button>
            <Button
              variant="outlined"
              color="secondary"
              onClick={() => navigate('/turmas')}
            >
              Cancelar
            </Button>
          </Box>
        </form>

        <Snackbar
          open={success}
          autoHideDuration={3000}
          onClose={() => setSuccess(false)}
          anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
        >
          <Alert onClose={() => setSuccess(false)} severity="success">
            Turma criada com sucesso!
          </Alert>
        </Snackbar>
      </Box>
    </Box>
  );
};