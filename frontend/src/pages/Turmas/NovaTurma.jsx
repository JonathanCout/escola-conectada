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
import { professorService } from '../../services/professorService';
import { disciplinaService } from '../../services/disciplinaService';
import { alunoService } from '../../services/alunoService';
import { useNavigate } from 'react-router-dom';

export const NovaTurma = () => {
  const [formData, setFormData] = useState({
    codigo: '',
    periodo: '',
    horario: {
      hour: 0,
      minute: 0,
      second: 0,
      nano: 0,
    },
    professorId: '',
    disciplinaId: '',
    alunosId: [],  // Aqui os alunos selecionados serão armazenados
  });
  const [professores, setProfessores] = useState([]);
  const [disciplinas, setDisciplinas] = useState([]);
  const [alunos, setAlunos] = useState([]);
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
      const [professoresResponse, disciplinasResponse, alunosResponse] =
        await Promise.all([
          professorService.getProfessores(),
          disciplinaService.getDisciplinas(),
          alunoService.getAlunos(),
        ]);

      setProfessores(Array.isArray(professoresResponse) ? professoresResponse : []);
      setDisciplinas(Array.isArray(disciplinasResponse) ? disciplinasResponse : []);
      setAlunos(Array.isArray(alunosResponse) ? alunosResponse : []);
    } catch (err) {
      setError('Erro ao carregar dados.');
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

  const handleHorarioChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      horario: {
        ...prevFormData.horario,
        [name]: parseInt(value, 10),
      },
    }));
  };

  // Ajuste para a seleção de múltiplos alunos
  const handleAlunosChange = (e) => {
    const selectedAlunos = Array.from(e.target.selectedOptions, (option) => parseInt(option.value, 10));
    setFormData((prevFormData) => ({
      ...prevFormData,
      alunosId: selectedAlunos,
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
        periodo: '',
        horario: { hour: 0, minute: 0, second: 0, nano: 0 },
        professorId: '',
        disciplinaId: '',
        alunosId: [],
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
          />
          <TextField
            label="Período"
            name="periodo"
            value={formData.periodo}
            onChange={handleChange}
            fullWidth
            required
            sx={{ mb: 2 }}
          />
          <TextField
            label="Hora"
            name="hour"
            type="number"
            value={formData.horario.hour}
            onChange={handleHorarioChange}
            fullWidth
            required
            sx={{ mb: 2 }}
            inputProps={{ min: 0, max: 23 }}
          />
          <TextField
            label="Minuto"
            name="minute"
            type="number"
            value={formData.horario.minute}
            onChange={handleHorarioChange}
            fullWidth
            required
            sx={{ mb: 2 }}
            inputProps={{ min: 0, max: 59 }}
          />
          <TextField
            label="Professor"
            name="professorId"
            value={formData.professorId}
            onChange={handleChange}
            fullWidth
            required
            select
            sx={{ mb: 2 }}
          >
            {professores.map((professor) => (
              <MenuItem key={professor.id} value={professor.id}>
                {professor.nome}
              </MenuItem>
            ))}
          </TextField>
          <TextField
            label="Disciplina"
            name="disciplinaId"
            value={formData.disciplinaId}
            onChange={handleChange}
            fullWidth
            required
            select
            sx={{ mb: 2 }}
          >
            {disciplinas.map((disciplina) => (
              <MenuItem key={disciplina.id} value={disciplina.id}>
                {disciplina.nome}
              </MenuItem>
            ))}
          </TextField>
          
          {/* Campo de seleção de múltiplos alunos */}
          <TextField
            label="Alunos"
            name="alunosId"
            value={formData.alunosId}
            onChange={handleAlunosChange}
            fullWidth
            required
            select
            sx={{ mb: 2 }}
            SelectProps={{
              multiple: true,  // Permite selecionar múltiplos alunos
            }}
          >
            {alunos.map((aluno) => (
              <MenuItem key={aluno.id} value={aluno.id}>
                {aluno.nome}
              </MenuItem>
            ))}
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