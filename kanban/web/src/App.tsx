import { Footer } from "./components/Footer";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Home } from "./pages/Home";
import { Login } from "./pages/Login";
import { Criar_Conta } from "./pages/Criar_Conta";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/criar_conta" element={<Criar_Conta />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
