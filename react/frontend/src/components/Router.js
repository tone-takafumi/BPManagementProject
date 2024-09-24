import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./Login";
import Content from "./Content";
import PrivateRoute from "./PrivateRoute";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route
                    path="/content"
                    element={
                            <Content />
                    }
                />
            </Routes>
        </Router>
    );
};

export default App;
