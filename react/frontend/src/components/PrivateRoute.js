import { Navigate } from "react-router-dom";

const PrivateRoute = ({ children }) => {
    const token = localStorage.getItem("token");

    if (!token) {
        return <Navigate to="/login" />;
    }

    // トークン検証ロジックも追加可能
    return children;
};

export default PrivateRoute;
