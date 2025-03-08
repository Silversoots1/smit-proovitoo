<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
</head>
<body>
    <h1>Palun sisestage enda kontaktandmed ning automargid, millest olete huvitatud</h1>
    <br>

    <form action="saveDetails" method="post">
        <label for="name">Ees- ja perekonnanimi:</label>
        <input type="text" id="name" name="name" required>
        <br>

        <label for="phone">Kontakttelefon:</label>
        <input type="text" id="phone" name="phone" required>
        <br>

        <label for="cars">Automargid:</label>
        <select id="cars" name="cars" multiple="">
            <option>Mercedes-Benz</option>
            <option>&nbsp;C klass</option>
            <option>&nbsp;&nbsp;C 160</option>
            <option>&nbsp;&nbsp;C 180</option>
            <option>&nbsp;&nbsp;C 200</option>
            <option>&nbsp;&nbsp;C 220</option>
            <option>BMW</option>
            <option>&nbsp;3 seeria</option>
            <option>&nbsp;&nbsp;315</option>
            <option>&nbsp;&nbsp;316</option>
            <option>&nbsp;&nbsp;317</option>
            <option>&nbsp;&nbsp;318</option>
            <option>&nbsp;&nbsp;319</option>
            <option>&nbsp;4 seeria</option>
            <option>&nbsp;5 seeria</option>
            <option>&nbsp;&nbsp;518</option>
            <option>&nbsp;&nbsp;520</option>
            <option>&nbsp;&nbsp;523</option>
            <option>&nbsp;&nbsp;524</option>
            <option>&nbsp;&nbsp;525</option>
            <option>Audi</option>
            <option>&nbsp;A seeria</option>
            <option>&nbsp;e-tron</option>
            <option>&nbsp;Q seeria</option>
            <option>&nbsp;&nbsp;Q2</option>
            <option>&nbsp;&nbsp;Q3</option>
            <option>&nbsp;&nbsp;Q4</option>
            <option>&nbsp;&nbsp;Q5</option>
            <option>&nbsp;&nbsp;Q7</option>
            <option>&nbsp;RS seeria</option>
            <option>&nbsp;&nbsp;RS4</option>
            <option>&nbsp;&nbsp;RS5</option>
            <option>&nbsp;&nbsp;RS6</option>
            <option>&nbsp;TT</option>
            <option>Citroën</option>
            <option>Muu</option>
        </select>
        <br>

        <label for="license">Kas Teil on kehtiv juhiluba?</label>
        <input type="checkbox" id="license" name="license">
        <br>

        <input type="submit" value="Salvesta">
    </form>
</body>
</html>