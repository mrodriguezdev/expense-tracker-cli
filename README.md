# expense-tracker-cli

Esta es una aplicación de línea de comandos para el seguimiento de gastos, diseñada para ayudar a los usuarios a administrar sus finanzas. La aplicación permite agregar, eliminar, visualizar y resumir gastos de manera eficiente. Este proyecto se basa en la propuesta de [Roadmap.sh](https://roadmap.sh/projects/expense-tracker)

## Requisitos
La aplicación debe ejecutarse desde la línea de comandos y debe tener las siguientes características:

- Los usuarios pueden agregar un gasto con una descripción y cantidad.
- Los usuarios pueden actualizar un gasto.
- Los usuarios pueden eliminar un gasto.
- Los usuarios pueden ver todos los gastos.
- Los usuarios pueden ver un resumen de todos los gastos.
- Los usuarios pueden ver un resumen de los gastos de un mes específico (del año en curso).

## Ejemplo

La lista de comandos y su salida esperada se muestra a continuación:

### Agregar un gasto
```bash
$ expense-tracker-cli add --description "Lunch" --amount 20
# Expense added successfully (ID: 1)
```

### Actualizar un gasto
```bash
$ expense-tracker-cli update --d 2 --description "Dinner" --amount 10
# Expense updated successfully (ID: 2)
```

### Eliminar un gasto
```bash
$ expense-tracker-cli delete --id 1
# Expense deleted successfully
```

### Ver todos los gastos
```bash
$ expense-tracker-cli list
# ID  Date       Description  Amount
# 1   2024-08-06  Lunch        $20
# 2   2024-08-06  Dinner       $10
```

### Resumen de gastos
```bash
$ expense-tracker-cli summary
# Total expenses: $30
```

### Resumen mensual
```bash
$ expense-tracker-cli summary --month 8
# Total expenses for August: $20
```

# Implementación
Para la implementación de esta aplicación, utilizaré las siguientes tecnologías:

- [**Java**](https://www.java.com/es/) con [**Maven**](https://maven.apache.org/) como gestor de dependencias.
- [**Picocli**](https://picocli.info/) como biblioteca para el análisis de comandos en proyectos de CLI con Java.
- [**CSV**](https://en.wikipedia.org/wiki/Comma-separated_values) como formato de archivo para el almacenamiento de los gastos.