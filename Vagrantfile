OS="centos/7"


PORTS_N1={ 8180 => 80,
	   5156 => 5556,
	   4143 => 443,
	   9101 => 9001,
	   9102 => 9002,
	   9103 => 9003 }

PORTS_N2={ 8280 => 80,
	   5256 => 5556,
	   4243 => 443,
	   9201 => 9001,
	   9202 => 9002,
	   9203 => 9003 }

PORTS_N3={ 8380 => 80,
	   5356 => 5556,
	   4343 => 443,
	   9301 => 9001,
	   9302 => 9002,
	   9303 => 9003 }

NODES=[{ :name => "k8s-n1", :ports => PORTS_N1, :id => "01" },
       { :name => "k8s-n2", :ports => PORTS_N2, :id => "02" },
       { :name => "k8s-n3", :ports => PORTS_N3, :id => "03" },
       { :name => "ansible-controller", :ports => {}, :id => "11" }]

SHELL_SCRIPT = <<-END
  sudo mv /etc/hosts /etc/hosts.original || true
  sudo mv hosts /etc/hosts
  chmod 0600 /home/vagrant/.ssh/id_rsa
  yum update
  yum install -y tree
  echo "$(date)" > /provisioned_at
END

Vagrant.configure("2") do |config|


  config.ssh.insert_key = false

  NODES.each do |node|
    config.vm.define node[:name] do |cfg|
      cfg.vm.box = OS
      cfg.vm.network "private_network", ip: "10.0.0.1#{node[:id]}"
      node[:ports].each do |port_host, port_guest|
        cfg.vm.network "forwarded_port", guest: port_guest, host: port_host
      end

      if node[:name] == "ansible-controller"
        cfg.vm.provision "ansible_local" do |ansible|
          ansible.playbook = "ansible/site.yml"
	  ansible.verbose = true
	  ansible.install = true
	  ansible.limit = "all"
	  ansible.inventory_path = "ansible/hosts"
	  ansible.config_file = "ansible/ansible.cfg"
	end
      end
    end
  end


  config.vm.provision "file", source: "~/.vagrant.d/insecure_private_key", destination: "/home/vagrant/.ssh/id_rsa"
  config.vm.provision "file", source: "shell/hosts", destination: "hosts"
  config.vm.provision "shell", inline: SHELL_SCRIPT

end
