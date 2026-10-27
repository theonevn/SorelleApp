using Dapper;
using SorelleApi.Models;

namespace SorelleApi.Data;

public class UsuarioRepository
{
    private readonly Db _db;

    public UsuarioRepository(Db db)
    {
        _db = db;
    }

    public async Task<Usuario?> BuscarPorEmailAsync(string email)
    {
        using var conn = _db.CreateConnection();

        const string sql = @"
            SELECT ID AS Id, Nome, Email, Senha
            FROM Usuarios
            WHERE Email = @Email";

        return await conn.QuerySingleOrDefaultAsync<Usuario>(sql, new { Email = email });
    }

    public async Task<int> CriarAsync(string nome, string email, string senhaHash)
    {
        using var conn = _db.CreateConnection();

        const string sql = @"
            INSERT INTO Usuarios (Nome, Email, Senha)
            OUTPUT INSERTED.ID
            VALUES (@Nome, @Email, @Senha)";

        return await conn.ExecuteScalarAsync<int>(sql,
            new { Nome = nome, Email = email, Senha = senhaHash });
    }
}