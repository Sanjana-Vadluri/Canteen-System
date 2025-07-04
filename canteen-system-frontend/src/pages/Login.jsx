import { useState, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import { login } from "../services/authService";
import { AuthContext } from "../context/AuthContext";
import foodimage from "../assets/register.png";

export default function Login() {
    const { saveToken } = useContext(AuthContext);
    const nav = useNavigate();
    const [form, setForm] = useState({ email: "", password: "" });
    const [err, setErr] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const { data } = await login(form.email, form.password);
            console.log(data);
            saveToken(data.token);
            nav("/canteens");
        } catch (ex) {
            setErr("Invalid credentials" + ex);
        }
    };

    return (

        <>
            <div className="flex h-screen bg-black">
                <div className="ml-80 w-1/2 flex items-center bg-black">
                    <img src={foodimage} alt="logo" className="max-h-full object-cover"/>
                </div>

                <div className="w-1/2 flex items-center justify-center bg-black mr-64">
                    <form
                        onSubmit={handleSubmit}
                        className="bg-[#121212] p-10 rounded-2xl shadow-lg w-[500px] text-white space-y-6"
                    >
                        <h1 className="text-3xl font-bold text-center">Login</h1>

                        <div>
                            <label htmlFor="email" className="block text-sm font-medium">Email:</label>
                            <input
                                id="email"
                                placeholder="Email"
                                className="w-full p-2 rounded-md border border-gray-400 bg-transparent"
                                value={form.email}
                                onChange={(e) => setForm({...form, email: e.target.value})}
                            />
                        </div>

                        <div>
                            <label htmlFor="password" className="block text-sm font-medium">Password:</label>
                            <input
                                id="password"
                                placeholder="Password"
                                type="password"
                                className="w-full p-2 rounded-md border border-gray-400 bg-transparent"
                                value={form.password}
                                onChange={(e) => setForm({...form, password: e.target.value})}
                            />
                        </div>
                        <p className="text-right text-xs mt-1 cursor-pointer hover:underline">Forgot Password?</p>
                        {err && <p className="text-red-500 text-sm">{err}</p>}

                        <button className="w-full bg-orange-600 py-2 rounded-md text-white font-semibold">Login</button>

                        <p className="text-center text-sm">
                            Do not have an account? <Link to="/register" className="text-amber-200">Register</Link>
                        </p>
                    </form>
                </div>

            </div>
        </>
    );
}
