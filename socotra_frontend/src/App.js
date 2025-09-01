import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/common/Navbar";



function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div className="content">
        <Routes>
          {/* <Route path="home" element={<Home/>}/> */}
        </Routes>

      </div>


    </BrowserRouter>

  )

}
export default App; 