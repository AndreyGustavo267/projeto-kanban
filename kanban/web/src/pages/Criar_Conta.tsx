import { Link } from 'react-router-dom';
import logo from '../assets/logo_copyon.png';
import './Login.css';

export function Criar_Conta() {
    return (
        <div className="d-flex align-items-center py-4 bg-body-tertiary h-100 login-wrapper">
            <main className="w-100 m-auto form-container">
                <form>
                    <img 
                        src={logo} 
                        className="mb-4" 
                        height="70" 
                        width="70" 
                        alt="Logo CopyOn"
                    />
                    
                    <h1 className="h3 mb-3 fw-normal">Crie sua Conta</h1>

                    <div className="form-floating mb-3">
                        <input 
                            type="text"
                            className="form-control" 
                            id="floatingName" 
                            placeholder="Seu Nome Completo" 
                            required
                        />
                        <label htmlFor="floatingName">Nome Completo</label>
                    </div>

                    <div className="form-floating mb-3"> 
                        <input 
                            type="email" 
                            className="form-control" 
                            id="floatingEmail" 
                            placeholder="usuario@gmail.com" 
                            required 
                        />
                        <label htmlFor="floatingEmail">E-mail</label>
                    </div>

                    <div className="form-floating mb-3">
                        <input 
                            type="password" 
                            className="form-control" 
                            id="floatingPassword" 
                            placeholder="Senha" 
                            required 
                        />
                        <label htmlFor="floatingPassword">Senha</label>
                    </div>

                    <div className="form-floating">
                        <input 
                            type="password" 
                            className="form-control" 
                            id="floatingConfirm" 
                            placeholder="Confirme a Senha" 
                            required
                        />
                        <label htmlFor="floatingConfirm">Confirme a Senha</label>
                    </div>

                    <button className="btn btn-primary w-100 py-2 my-3" type="submit">
                        Criar Conta
                    </button>
                    
                    <Link to="/">Entrar</Link>
                    
                    <p className="text-body-secondary mt-5 mb-3">© 2024-2025</p>
                </form>
            </main>
        </div>
    );
}