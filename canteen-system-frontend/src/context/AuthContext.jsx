import { createContext, useEffect, useState } from "react";
import {jwtDecode} from "jwt-decode";
import {useContext} from "react";

export const AuthContext = createContext();

export default function AuthProvider({ children }) {
    const [token, setToken] = useState(localStorage.getItem("token"));
    const [user, setUser] = useState(token ? jwtDecode(token) : null);

    const saveToken = (jwt) => {
        localStorage.setItem("token", jwt);
        setToken(jwt);
        setUser(jwtDecode(jwt));
        console.log(user)
    };

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
        setUser(null);
    };

    // refresh user if token changes (e.g. on login/logout)
    useEffect(() => {
        if (token) setUser(jwtDecode(token));
    }, [token]);

    return (
        <AuthContext.Provider value={{ token, user, saveToken, logout }}>
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => useContext(AuthContext);
