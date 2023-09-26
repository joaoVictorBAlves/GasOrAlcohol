# Gasolina ou Álcool
 
> **Aluno:** João Victor Alves
> 
> **Matrícula:** 509697
> 
> **Disciplina:** Programação para Dispositivos Móveis
> 
> **Professor:** Dr. Windson Viana

## Atividade:

Você deve fazer um aplicativo que facilite a vida de quem vai decidir se vai usar Álcool ou Gasolina no carro. Ele pode ter apenas uma única tela conforme mostrado. Você pode usar o [código base](https://github.com/windcarvalho/AlcoolOuGasolina) fornecido pelo professor ou construir o seu. 
As features principais são:
1. **(2 pontos)** - Por enquanto, o aplicativo calcula qual dos combustíveis é o melhor baseado em dois tipos de cálculo: O álcool para ser rentável deve ter 70% ou 75% do valor da gasolina.
2. **(2 pontos)** - O app deve ter um ícone distinto do projeto do professor
3. **(2 pontos)** - As cores dos temas escuro e padrão devem ser modificados e deve ocorrer melhoria no Layout dos componentes
4. **(2 pontos)** - Ao clicar no switch, o valor de uma variável de cálculo deve ser mudada. 
5. **(2 pontos)** - A variável usada para o cálculo do percentual deve ser salva e recuperada no onCreate

## Cálculo do melhor combustível:
Para a realização do cálculo do melhor combustível foi utilizado a variável `percentual`, seguindo o princípio de que o álcool é mais rentável quando tiver 70% ou 75% do valor da gasolina. 
Assim, o primeiro passo é obter os valores e estados dos componentes `edGas` e `edAlcohol` que são os _inputs_ para os preços dos combustíveis, seguido do `Switch` que controla o valor do percentual em 70% ou 75%, o `botão` para executar o cálculo e o campo de texto `result` para mostrar o resultado.

```kotlin
    private var percent:Double = 0.7
    private lateinit var btCalc: Button
    private lateinit var switchPercent: Switch
    private lateinit var edGas: EditText
    private lateinit var edAlcohol: EditText
    private lateinit var result: TextView
```
```kotlin
        /**
         * When click in button the operation runs
         */
        btCalc.setOnClickListener(View.OnClickListener {
            result.text = ""
            if (edGas.text.isNotEmpty() && edAlcohol.text.isNotEmpty()) {
                val alcohol: Double = edAlcohol.text.toString().toDouble()
                val gas: Double = edGas.text.toString().toDouble()
                if (alcohol <= gas * percent) {
                    result.text = "It's better to buy Alcohol"
                } else {
                    result.text = "It's better to buy Gas"
                }
            }
        })
        /**
         * Change the factor of operation
         * */
        switchPercent.setOnClickListener(View.OnClickListener {
            percent = if(percent == 0.70){
                0.75
            }else{
                0.70
            }
        })
```

## Ícone, Paleta de Cores e Novo Layout:
| Ícone                                   | Paleta de Cores                                                        |
| --------------------------------------- | ---------------------------------------------------------------------- |
| <img src="https://github.com/joaoVictorBAlves/GasolinaOuAlcool/assets/86852231/0ad4c190-94bf-43bd-8906-1cfc634ee68b" width="200"> | ![paleta-cores](https://github.com/joaoVictorBAlves/GasolinaOuAlcool/assets/86852231/a67a927c-e2f2-4dc3-a6e8-b3282dcbcb84) |

### Novo layout e temas:
Com base nos dois tópicos anteriores e na tela já existente, os temas _claro_ e _escuro_ foram mudados e também ocorreu melhorias de hierarquia e ajuste nas margens dos componentes.
| Layout Claro                                                  | Layout Escuro                                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <img src="https://github.com/joaoVictorBAlves/GasolinaOuAlcool/assets/86852231/b25dfe10-88c7-4173-a579-0baa5d9f9716" width="500"> | <img src="https://github.com/joaoVictorBAlves/GasolinaOuAlcool/assets/86852231/0a5d37da-ed61-4e8a-8117-66806335ddb0" width="500"> |

## Usando o SharedPreferences para recuperar o estado:
Por fim, foi implementado o mecanismo de Shared Preferences para guardar os valores dos estados de todos os compoenentes e recuperá-los no `onCreate`.

```kotlin
/**
 * Global
 */
private lateinit var sharedPreferences: SharedPreferences
```
```kotlin
/**
 * Dentro do onCreate
 */
sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
```
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)

        // Initialize UI elements after setContentView
        btCalc = findViewById(R.id.btCalcular)
        switchPercent = findViewById(R.id.swPercent)
        edGas = findViewById(R.id.edGas)
        edAlcohol = findViewById(R.id.edAlcohol)
        result = findViewById(R.id.edResult)

        /**
         * Recover state
         * */
        edGas.setText(sharedPreferences.getString("gasValue", ""))
        edAlcohol.setText(sharedPreferences.getString("alcoholValue", ""))
        result.text = sharedPreferences.getString("result", "")
        switchPercent.isChecked = sharedPreferences.getBoolean("switchState", false)

        // Restante do código
}
```
Na função onSaveInstanceState é salvo os estados de todos os componentes usando o sharedPreferences
```kotlin
 override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(sharedPreferences.edit()) {
            putString("gasValue", edGas.text.toString())
            putString("alcoholValue", edAlcohol.text.toString())
            putString("result", result.text.toString())
            putBoolean("switchState", switchPercent.isChecked)
            apply()
        }
    }
```
## Resultado final
| Layout Claro | Layout Escuro |
| ------------ | ------------ |
| ![gasOrAlcohol](https://github.com/joaoVictorBAlves/GasolinaOuAlcool/assets/86852231/01605484-c66e-440b-aa0a-82a66723f361) | ![gasOrAlcoholDark](https://github.com/joaoVictorBAlves/GasolinaOuAlcool/assets/86852231/ea356104-f1da-46c5-8805-83c30f87423b) |


