import { useEffect, useState } from 'react'
import api from './api'

function App() {
  const [customers, setCustomers] = useState([])
  const [types, setTypes] = useState([])
  const [form, setForm] = useState({ name: '', email: '', customerTypeId: '' })

  useEffect(() => {
    api.get('/customers').then(res => setCustomers(res.data))
    api.get('/customer-types').then(res => setTypes(res.data))
  }, [])

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      const res = await api.post('/customers', form)
      setCustomers([...customers, res.data])
      setForm({ name: '', email: '', customerTypeId: '' })
    } catch (error) {
      console.error('Error al agregar cliente:', error)
    }
  }

  const deleteCustomer = async (id) => {
    try {
      await api.delete(`/customers/${id}`)
      setCustomers(customers.filter(c => c.id !== id))
    } catch (error) {
      console.error('Error al eliminar cliente:', error)
    }
  }

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Clientes</h2>
      <ul className="list-group mb-4">
        {customers.map(c => (
          <li key={c.id} className="list-group-item d-flex justify-content-between align-items-center">
            {c.name} ({c.email}) - Tipo: {types.find(t => t.id === c.customerTypeId)?.customerTypeName}
            <button className="btn btn-danger btn-sm" onClick={() => deleteCustomer(c.id)}>Eliminar</button>
          </li>
        ))}
      </ul>

      <h3>Nuevo Cliente</h3>
      <form onSubmit={handleSubmit} className="row g-3">
        <div className="col-md-4">
          <input
            className="form-control"
            placeholder="Nombre"
            value={form.name}
            onChange={e => setForm({ ...form, name: e.target.value })}
          />
        </div>
        <div className="col-md-4">
          <input
            className="form-control"
            placeholder="Email"
            value={form.email}
            onChange={e => setForm({ ...form, email: e.target.value })}
          />
        </div>
        <div className="col-md-3">
          <select
            className="form-select"
            value={form.customerTypeId}
            onChange={e => setForm({ ...form, customerTypeId: e.target.value })}
          >
            <option value="">Seleccionar tipo</option>
            {types.map(t => (
              <option key={t.id} value={t.id}>{t.customerTypeName}</option>
            ))}
          </select>
        </div>
        <div className="col-md-1">
          <button type="submit" className="btn btn-primary w-10">Agregar</button>
        </div>
      </form>
    </div>
  )
}

export default App
