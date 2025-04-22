import { useEffect, useState } from 'react'
import api from './api'

function CustomerPage() {
  const [customers, setCustomers] = useState([])
  const [types, setTypes] = useState([])
  const [form, setForm] = useState({ name: '', email: '', customerTypeId: '' })
  const [editingId, setEditingId] = useState(null)

  useEffect(() => {
    api.get('/customers').then(res => setCustomers(res.data))
    api.get('/customer-types').then(res => setTypes(res.data))
  }, [])

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      if (editingId) {
        const res = await api.put(`/customers/${editingId}`, form)
        setCustomers(customers.map(c => c.id === editingId ? res.data : c))
        setEditingId(null)
      } else {
        const res = await api.post('/customers', form)
        setCustomers([...customers, res.data])
      }
      setForm({ name: '', email: '', customerTypeId: '' })
    } catch (error) {
      console.error('Error al guardar cliente:', error)
    }
  }

  const deleteCustomer = async (id) => {
    try {
      await api.delete(`/customers/${id}`)
      setCustomers(customers.filter(c => c.id !== id))
      if (editingId === id) {
        setEditingId(null)
        setForm({ name: '', email: '', customerTypeId: '' })
      }
    } catch (error) {
      console.error('Error al eliminar cliente:', error)
    }
  }

  const startEdit = (customer) => {
    setForm({
      name: customer.name,
      email: customer.email,
      customerTypeId: customer.customerTypeId
    })
    setEditingId(customer.id)
  }

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Clientes</h2>
      <ul className="list-group mb-4">
        {customers.map(c => (
          <li key={c.id} className="list-group-item d-flex justify-content-between align-items-center">
            {c.name} ({c.email}) - Tipo: {types.find(t => t.id === c.customerTypeId)?.customerTypeName}
            <div className="btn-group">
              <button className="btn btn-success btn-sm" onClick={() => startEdit(c)}>Editar</button>
              <button className="btn btn-danger btn-sm" onClick={() => deleteCustomer(c.id)}>Eliminar</button>
            </div>
          </li>
        ))}
      </ul>

      <h3>{editingId ? 'Editar Cliente' : 'Nuevo Cliente'}</h3>
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
          <button type="submit" className="btn btn-primary w-100">
            {editingId ? 'Actualizar' : 'Agregar'}
          </button>
        </div>
      </form>
    </div>
  )
}

export default CustomerPage
