import React, { useState, useEffect } from 'react'; 
import { 
  Box, 
  Typography, 
  TextField, 
  Button, 
  MenuItem, 
  CircularProgress, 
  Snackbar, 
  Alert 
} from '@mui/material';
import { turmaService } from '../../services/turmaService';
import { useNavigate } from 'react-router-dom';

export const NovaTurma = () => {
  const [form, setForm] = useState({
    codigo: '',
    nome: '',
    ano: '',
    professor: '',
    disciplina: ''
  });
  const [professores, setProfessores] = useState([]);
  const [disciplinas, setDisciplinas] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [sucesso, setSucesso] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    carregarDados();
  }, []);

  const carregarDados = async () => {
    try {
      // Simula a chamada de API para carregar professores e disciplinas
      const professoresResponse = await turmaService.getProfessores();
      const disciplinasResponse = await turmaService.getDisciplinas();
      setProfessores(professoresResponse);
      setDisciplinas(disciplinasResponse);
    } catch (err) {
      setError('Erro ao carregar dados.');
      console.error(err);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setSucesso(false);
    setLoading(true);

    try {
      await turmaService.createTurma(form);
      setSucesso(true);
      setForm({
        codigo: '',
        nome: '',
        ano: '',
        professor: '',
        disciplina: ''
      });
    } catch (err) {
      setError('Erro ao criar turma.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box sx={{ p: 4, backgroundColor: '#f9f9f9', borderRadius: 2, boxShadow: 3 }}>
      <Typography variant="h4" sx={{ mb: 3, textAlign: 'center', fontWeight: 'bold' }}>
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
          value={form.codigo}
          onChange={handleInputChange}
          fullWidth
          required
          sx={{ mb: 2 }}
          inputProps={{ maxLength: 10 }}
        />
        <TextField
          label="Nome"
          name="nome"
          value={form.nome}
          onChange={handleInputChange}
          fullWidth
          required
          sx={{ mb: 2 }}
        />
        <TextField
          label="Ano"
          name="ano"
          type="number"
          value={form.ano}
          onChange={handleInputChange}
          fullWidth
          required
          sx={{ mb: 2 }}
          inputProps={{ min: 2000, max: 2100 }}
        />
        <TextField
          label="Professor"
          name="professor"
          value={form.professor}
          onChange={handleInputChange}
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
          value={form.disciplina}
          onChange={handleInputChange}
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

      {/* Snackbar para exibir mensagens de sucesso */}
      <Snackbar
        open={sucesso}
        autoHideDuration={3000}
        onClose={() => setSucesso(false)}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      >
        <Alert onClose={() => setSucesso(false)} severity="success">
          Turma criada com sucesso!
        </Alert>
      </Snackbar>
    </Box>
  );
};
