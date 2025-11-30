import './Login.css'
import { Link } from 'react-router-dom';
import logo from '../assets/logo_copyon.png';

export function Login() {
    return (
        <>
            <div className="login-wrapper bg-body-tertiary">
                <main className="w-100 m-auto form-container">
                    <form>
                    {/* Imagem importada via variável */}
                    <img 
                        src={logo} 
                        className="mb-4" 
                        height="70" 
                        width="70" 
                        alt="Logo CopyOn" 
                    />
                    
                    <h1 className="h3 mb-3 fw-normal">Entre</h1>

                    <div className="form-floating">
                        <input 
                        type="email" 
                        className="form-control" 
                        id="floatingInput" 
                        placeholder="usuario@gmail.com" 
                        />
                        <label htmlFor="floatingInput">E-mail</label>
                    </div>

                    <div className="form-floating">
                        <input 
                        type="password" 
                        className="form-control" 
                        id="floatingPassword" // Mudei o ID para não duplicar com o de cima
                        placeholder="senha" 
                        />
                        <label htmlFor="floatingPassword">Senha</label>
                    </div>

                    <div className="form-check text-start my-3">
                        <input 
                        type="checkbox" 
                        className="form-check-input" 
                        id="flexCheckDefault" 
                        />
                        <label className="form-check-label" htmlFor="flexCheckDefault">
                        Lembrar senha
                        </label>
                    </div>

                    <button className="btn btn-primary w-100 py-2 my-3" type="submit">
                        Entrar
                    </button>
                    
                    {/* Em React usaremos Link do react-router no futuro, por enquanto <a> serve */}
                    <Link to="/criar_conta">Criar conta</Link>
                    
                    <p className="text-body-secondary mt-5 mb-3">© 2024-2025</p>
                    </form>
                </main>
            </div>
        </>
    );
}

export default Login;