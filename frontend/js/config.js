// Configuración de la API
const API_URL = 'http://localhost:8080/api';

// Endpoints
const API_ENDPOINTS = {
    insumos: `${API_URL}/insumos`,
    platos: `${API_URL}/platos`,
    categorias: `${API_URL}/categorias`,
    ventas: `${API_URL}/ventas`
};

// Función auxiliar para manejar errores
function handleError(error) {
    console.error('Error:', error);
    alert('Ocurrió un error. Por favor, intenta nuevamente.');
}

// Función auxiliar para formatear fecha
function formatDate(date) {
    const d = new Date(date);
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}
