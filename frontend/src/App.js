import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { Layout } from './components/Layout/Layout';
import { ListaAlunos } from './pages/Alunos/ListaAlunos';
import { ListaTurmas } from './pages/Turmas/ListaTurmas';
import { ListaAvaliacoes } from './pages/Avaliacoes/ListaAvaliacoes';
import { ListaMerendas } from './pages/Merendas/ListaMerendas';
import { ListaEventos } from './pages/Eventos/ListaEventos';
import { ListaConversas } from './pages/Conversas/ListaConversas';
import { Login } from './pages/Login/Login';
import { authService } from './services/authService';
import { NovoAluno } from './pages/Alunos/NovoAluno';
import { EditarAluno } from './pages/Alunos/EditarAluno';
import { ListaProfessores } from './pages/Professores/ListaProfessores';
import {NovoProfessor}  from './pages/Professores/NovoProfessor';
import {EditarProfessores}  from './pages/Professores/EditarProfessor';
import {Chat} from './pages/Chat/Chat'; 
import { NovaTurma } from './pages/Turmas/NovaTurma.jsx';

const ProtectedRoute = ({ children }) => {
  if (!authService.isAuthenticated()) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route
          path="/*"
          element={
            <ProtectedRoute>
            <Layout>
              <Routes>
                <Route path="/alunos" element={<ListaAlunos />} />
                <Route path="/alunos/novo" element={<NovoAluno />} />
                <Route path="/alunos/editar/:id" element={<EditarAluno />} />
                <Route path="/turmas" element={<ListaTurmas />} />
                <Route path="/turmas/novaturma" element={<NovaTurma />} />
                {/* <Route path="/turmas/editar/" element={<EditarTurma />} /> */}
                <Route path="/avaliacoes" element={<ListaAvaliacoes />} />
                <Route path="/merendas" element={<ListaMerendas />} />
                <Route path="/eventos" element={<ListaEventos />} />
                <Route path="/conversas" element={<ListaConversas />} />
                <Route path="/professores" element={<ListaProfessores />} />
                <Route path="/professores/novo" element={<NovoProfessor />} />
                <Route path="/professores/editar/:id" element={<EditarProfessores />} />
                <Route path="/chat" element={<Chat />} />
              </Routes>
            </Layout>
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;