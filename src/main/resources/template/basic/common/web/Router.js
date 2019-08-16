          { // ${tableComment}
            path: '/app/${module}/${simpleName_LXX}',
              component: './${module}/${simpleName_LXX}/${simpleName_class}',
          },
          {
            path: '/app/${module}/${simpleName_LXX}/add',
              component: './${module}/${simpleName_LXX}/${simpleName_class}Detail',
          },
          {
            path: '/app/${module}/${simpleName_LXX}/:openType/:${simpleName_TF}Id',
              component: './${module}/${simpleName_LXX}/${simpleName_class}Detail',
          },
