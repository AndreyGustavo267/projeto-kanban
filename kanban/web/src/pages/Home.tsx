import './Home.css';
import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../assets/logo_copyon.png';

const API_BASE_URL = 'http://localhost:8080/api';

interface Page {
  id: number;
  title: string;
  path: string;
}

interface UserData {
  id: number;
  name: string;
  email: string;
  pages: Page[];
}

export function Home() {
  const [isLoading, setIsLoading] = useState(true);
  const [userData, setUserData] = useState<UserData | null>(null);
  const navigate = useNavigate();

  const fetchUserData = async () => {
    setIsLoading(true);
    try {
      const email = localStorage.getItem('email'); // <--- pegando email do login
      if (!email) throw new Error('Usuário não encontrado');

      const token = localStorage.getItem('token');
      const response = await fetch(`${API_BASE_URL}/usuario/email/${email}`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      if (!response.ok) throw new Error('Erro ao buscar dados do usuário');

      const data = await response.json();
      const formattedData: UserData = {
        id: data.idUsuario,
        name: data.nome,
        email: data.email,
        pages: data.paginas.map((p: any) => ({
          id: p.idPagina,
          title: p.nome,
          path: `/page/${p.idPagina}`
        }))
      };
      setUserData(formattedData);
    } catch (error) {
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchUserData();
  }, []);

  const handleCreatePage = async () => {
    if (!userData) return;
    try {
      const response = await fetch(`${API_BASE_URL}/pagina`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          nome: 'Nova Página',
          idUsuario: userData.id
        })
      });
      if (!response.ok) throw new Error('Erro ao criar página');

      const newPage = await response.json();
      setUserData(prev => prev ? {
        ...prev,
        pages: [...prev.pages, {
          id: newPage.idPagina,
          title: newPage.nome,
          path: `/page/${newPage.idPagina}`
        }]
      } : null);
    } catch (error) {
      console.error(error);
    }
  };

  const handleViewPage = (pagePath: string) => navigate(pagePath);

  const renderPageItem = (page: Page) => (
    <div
      key={page.id}
      className="card list-item-card mb-2 bg-dark text-white border-left-default"
      onClick={() => handleViewPage(page.path)}
    >
      <div className="card-body d-flex justify-content-between align-items-center py-2 px-3">
        <div className="d-flex flex-column text-start">
          <span className="h6 mb-0">{page.title}</span>
        </div>
        <i className="fa-solid fa-chevron-right text-muted small"></i>
      </div>
    </div>
  );

  const mainContent = () => {
    if (isLoading) {
      return (
        <div className="col-12 loader-container d-flex justify-content-center pt-5">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Carregando...</span>
          </div>
        </div>
      );
    }

    if (!userData || userData.pages.length === 0) {
      return (
        <div className="col-12 mt-5">
          <div className="alert alert-info text-center" role="alert">
            Olá {userData?.name || 'Usuário'}! Nenhuma página encontrada. Clique em "Criar Página" para começar!
          </div>
        </div>
      );
    }

    return (
      <div className="col-12 mt-4">
        <h3 className="h5 mb-3 text-white">Acesso Rápido</h3>
        {userData.pages.map(renderPageItem)}
      </div>
    );
  };

  return (
    <div className="d-flex flex-column h-100 bg-body-tertiary">
      <nav className="navbar navbar-expand-lg navbar-dark fixed-top">
        <div className="container">
          <Link className="navbar-brand d-flex align-items-center" to="#" onClick={() => navigate('/home')}>
            <img src={logo} className="mb-4" height="40" width="40" alt="Logo CopyOn" />
            <strong>CopyOn</strong>
          </Link>
          <div className="d-flex align-items-center">
            <span className="text-secondary me-3 d-none d-md-block">
              {userData ? `Online - ${userData.name}` : 'Online'}
            </span>
            <button className="btn btn-outline-light btn-sm rounded-circle">
              <i className="fa-solid fa-user"></i>
            </button>
          </div>
        </div>
      </nav>

      <main className="container py-5 mt-5 flex-grow-1">
        <div id="home-view">
          <h1 className="display-4 mb-5 text-white">
            {userData ? `Bem vindo, ${userData.name}!` : 'Bem vindo!'}
          </h1>

          <div className="d-flex justify-content-between align-items-center mb-4">
            <h2 className="h4 mb-0 text-white">Suas páginas</h2>
            <div className="d-flex gap-2">
              <button className="btn btn-sm btn-primary" onClick={handleCreatePage}>
                <i className="fa-solid fa-plus me-1"></i> Criar Página
              </button>
            </div>
          </div>

          <div className="row g-4" id="notes-grid">
            {mainContent()}
          </div>
        </div>
      </main>

      <footer className="footer mt-auto py-3 bg-dark border-top border-secondary">
        <div className="container text-center">
          <span className="text-muted small">&copy; 2025 CopyOn. Todos os direitos reservados.</span>
        </div>
      </footer>
    </div>
  );
}
