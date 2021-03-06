<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version = "1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">
    <xsl:template match = "/">
        <html>
            <body>
                <h2>Alkalmazott</h2>
                <table border = "3">
                    <tr bgcolor = "red">
                        <th>ID</th>
                        <th>Keresztnev</th>
                        <th>Vezeteknev</th>
                        <th>becenev</th>
                        <th>fizetes</th>
                    </tr>
                    <xsl:for-each select="osztaly/alkalmazott">
                        <tr>
                            <td>
                                <xsl:value-of select = "@id"/>
                            </td>
                            <td><xsl:value-of select = "keresznev"/></td>
                            <td><xsl:value-of select = "vezeteknev"/></td>
                            <td><xsl:value-of select = "becenev"/></td>
                            <td><xsl:value-of select = "fizetes"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>