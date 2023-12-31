﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using TicketApp.Server.DAL;

#nullable disable

namespace TicketApp.Server.Migrations
{
    [DbContext(typeof(TicketContext))]
    [Migration("20230702184548_Clientes y su detalle")]
    partial class Clientesysudetalle
    {
        /// <inheritdoc />
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder.HasAnnotation("ProductVersion", "7.0.7");

            modelBuilder.Entity("TicketApp.Shared.Clientes", b =>
                {
                    b.Property<int>("ClienteId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Direccion")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<int>("LimiteCredito")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Nombres")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<string>("Rnc")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.HasKey("ClienteId");

                    b.ToTable("Clientes");
                });

            modelBuilder.Entity("TicketApp.Shared.ClientesDetalle", b =>
                {
                    b.Property<int>("DetalleId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<int>("ClienteId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Telefono")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<int>("TipoId")
                        .HasColumnType("INTEGER");

                    b.HasKey("DetalleId");

                    b.HasIndex("ClienteId");

                    b.ToTable("ClientesDetalle");
                });

            modelBuilder.Entity("TicketApp.Shared.Tickets", b =>
                {
                    b.Property<int>("TicketId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Asunto")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<int>("ClienteId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Descripcion")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<DateTime>("Fecha")
                        .HasColumnType("TEXT");

                    b.Property<string>("SolicitadoPor")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.HasKey("TicketId");

                    b.ToTable("Tickets");
                });

            modelBuilder.Entity("TicketApp.Shared.TiposTelefonos", b =>
                {
                    b.Property<int>("TipoId")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Descripcion")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.HasKey("TipoId");

                    b.ToTable("TiposTelefonos");

                    b.HasData(
                        new
                        {
                            TipoId = 1,
                            Descripcion = "Telefono"
                        },
                        new
                        {
                            TipoId = 2,
                            Descripcion = "Celular"
                        },
                        new
                        {
                            TipoId = 3,
                            Descripcion = "Oficina"
                        });
                });

            modelBuilder.Entity("TicketApp.Shared.ClientesDetalle", b =>
                {
                    b.HasOne("TicketApp.Shared.Clientes", null)
                        .WithMany("ClientesDetalle")
                        .HasForeignKey("ClienteId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("TicketApp.Shared.Clientes", b =>
                {
                    b.Navigation("ClientesDetalle");
                });
#pragma warning restore 612, 618
        }
    }
}
