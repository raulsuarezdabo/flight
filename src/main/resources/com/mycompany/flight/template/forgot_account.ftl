<#import "layout.ftl" as template>
<@template.header title=title></@template.header>
<table class="row">
<tr>
    <td class="wrapper last">
        <table class="twelve columns">
            <tr>
                <td>
                    <h1>${wellcome}</h1>
                    <p class="lead">${forgotText}</p>
                    <a href="${forgotLink}">${access}</a>
                </td>
                <td class="expander"></td>
            </tr>
        </table>
    </td>
</tr>
</table>
<@template.footer></@template.footer>