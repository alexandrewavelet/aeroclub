$ ->
  jsRoutes.controllers.Application.getI18nMessages('views.index.login.error1,views.index.login.error2').ajax
    success: (messages) ->
      console.log messages
      $('#login').submit (e) ->
        do e.preventDefault

        $username        = $(@).find '#username'
        $password        = $(@).find '#password'
        $message         = $ '#message'
        $notAuthentified = $ '#not-authentified'

        username  = $username.val().trim()
        password  = $password.val().trim()

        if username is '' or password is ''
          $message
            .attr('class', 'alert alert-error')
            .fadeIn()
            .html messages['views.index.login.error1']
        else
          jsRoutes.controllers.Application.authenticate().ajax
            data: JSON.stringify {username, password}
            contentType: 'application/json'
            success: (data) =>
              $message
                .attr('class', "alert alert-#{data.result}")
                .fadeIn()
                .html data.message
              if data.result is 'success'
                $notAuthentified
                  .fadeIn()
                  .find('#username')
                    .append(username)
                $(@).fadeOut()
            error: ->
              $message
                .attr('class', 'alert alert-error')
                .fadeIn()
                .html messages['views.index.login.error2']