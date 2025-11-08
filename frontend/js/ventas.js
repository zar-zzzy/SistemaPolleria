// Script para la página de Ventas

let platos = [];

// Cargar datos al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    cargarPlatos();
    cargarVentas();
    
    // Manejar el envío del formulario
    document.getElementById('form-venta').addEventListener('submit', function(e) {
        e.preventDefault();
        registrarVenta();
    });
});

// Función para cargar platos en el select
async function cargarPlatos() {
    try {
        const response = await fetch(API_ENDPOINTS.platos);
        platos = await response.json();
        
        const selectPlato = document.getElementById('plato');
        selectPlato.innerHTML = '<option value="">Seleccionar plato</option>';
        
        platos.forEach(plato => {
            const option = document.createElement('option');
            option.value = plato.idPlato;
            option.setAttribute('data-precio', plato.precio);
            option.textContent = `${plato.nombre} - S/ ${plato.precio.toFixed(2)}`;
            selectPlato.appendChild(option);
        });
    } catch (error) {
        handleError(error);
    }
}

// Función para calcular el total
function calcularTotal() {
    var selectPlato = document.getElementById('plato');
    var cantidad = parseFloat(document.getElementById('cantidad').value) || 0;
    var precioUnitario = 0;
    
    if (selectPlato.selectedIndex > 0) {
        precioUnitario = parseFloat(selectPlato.options[selectPlato.selectedIndex].getAttribute('data-precio')) || 0;
    }
    
    var total = precioUnitario * cantidad;
    document.getElementById('total').value = 'S/ ' + total.toFixed(2);
}

// Función para cargar todas las ventas
async function cargarVentas() {
    try {
        const response = await fetch(API_ENDPOINTS.ventas);
        const ventas = await response.json();
        
        const tbody = document.querySelector('table tbody');
        tbody.innerHTML = '';
        
        ventas.forEach(venta => {
            const tr = document.createElement('tr');
            const fecha = formatDate(venta.fecha);
            
            // Obtener información del plato principal (si tiene detalles)
            let platoInfo = 'N/A';
            let cantidad = 0;
            let precioUnit = 0;
            let metodoPago = 'Efectivo';
            
            if (venta.detalles && venta.detalles.length > 0) {
                const detalle = venta.detalles[0];
                if (detalle.plato) {
                    platoInfo = detalle.plato.nombre;
                    cantidad = detalle.cantidad;
                    precioUnit = detalle.precio;
                }
            }
            
            tr.innerHTML = `
                <td>${venta.id}</td>
                <td>${venta.cliente || 'Cliente General'}</td>
                <td>${platoInfo}</td>
                <td>${cantidad}</td>
                <td>S/ ${precioUnit.toFixed(2)}</td>
                <td>S/ ${venta.total.toFixed(2)}</td>
                <td>${metodoPago}</td>
                <td>${fecha}</td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        handleError(error);
    }
}

// Función para registrar una nueva venta
async function registrarVenta() {
    const cliente = document.getElementById('cliente').value || 'Cliente General';
    const platoId = document.getElementById('plato').value;
    const cantidad = parseInt(document.getElementById('cantidad').value);
    const metodoPago = document.getElementById('metodo-pago').value;
    
    if (!platoId) {
        alert('Por favor, selecciona un plato');
        return;
    }
    
    if (!metodoPago) {
        alert('Por favor, selecciona un método de pago');
        return;
    }
    
    // Buscar el plato seleccionado
    const platoSeleccionado = platos.find(p => p.idPlato == platoId);
    
    if (!platoSeleccionado) {
        alert('Plato no encontrado');
        return;
    }
    
    const precioUnitario = platoSeleccionado.precio;
    const total = precioUnitario * cantidad;
    
    const nuevaVenta = {
        cliente: cliente,
        fecha: new Date().toISOString(),
        total: total,
        detalles: [
            {
                plato: { idPlato: parseInt(platoId) },
                cantidad: cantidad,
                precio: precioUnitario,
                subtotal: total
            }
        ]
    };
    
    try {
        const response = await fetch(API_ENDPOINTS.ventas, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevaVenta)
        });
        
        if (response.ok) {
            alert('Venta registrada exitosamente');
            document.getElementById('form-venta').reset();
            document.getElementById('total').value = '';
            cargarVentas();
        } else {
            alert('Error al registrar la venta');
        }
    } catch (error) {
        handleError(error);
    }
}
